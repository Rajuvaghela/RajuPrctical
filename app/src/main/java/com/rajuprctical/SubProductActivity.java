package com.rajuprctical;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.rajuprctical.Adapter.MainProductAdapter;
import com.rajuprctical.Adapter.SubProductAdapter;
import com.rajuprctical.Model.ProductModel;
import com.rajuprctical.Model.SubProductModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class SubProductActivity extends AppCompatActivity {

    String product_id;

    ProgressDialog dialog;
    RecyclerView rv_sub_product_list;
    ArrayList<SubProductModel> product_models=new ArrayList<>();
    SubProductAdapter mainProductAdapter;

    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_product);

        Bundle bundle = getIntent().getExtras();
         product_id = bundle.getString("product_id");
        if (product_id ==null){
            finish();
        }

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading..");
        dialog.setCancelable(false);
        rv_sub_product_list=(RecyclerView)findViewById(R.id.rv_sub_product_list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        rv_sub_product_list.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        GetProductLst();

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void GetProductLst() {
        if (isNetworkAvailable()) {
            dialog.show();
            final AsyncHttpClient client = new AsyncHttpClient();
            client.setTimeout(800000);
            RequestParams params = new RequestParams();
              params.add("catslug", product_id);

            client.post( "http://phpjoomlacoders.com/ios/?webservice=1&vootouchservice=get_products", params, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    dialog.dismiss();
                    Log.e("fail_response", responseString);
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    if (responseString != null) {
                        product_models.clear();

                        try {
                            Log.e("rv_response", responseString);

                          /*  JSONObject jsonObj1 = new JSONObject(responseString);
                            String str =jsonObj1.getString("data");
                            JSONObject jsonObj = new JSONObject(responseString);*/
                            //  JSONObject json= (JSONObject) new JSONTokener("data").nextValue();
                            JSONObject jsonObj1 = new JSONObject(responseString);
                            JSONObject jsonObj = jsonObj1.getJSONObject("data");
                            JSONArray resultsarray = jsonObj.getJSONArray("products");
                            for (int i = 0; i < resultsarray.length(); i++) {
                                SubProductModel temp=new SubProductModel();
                                temp.setImage(resultsarray.getJSONObject(i).getString("image"));
                                temp.setTitle(resultsarray.getJSONObject(i).getString("title"));
                                temp.setProduct_id(resultsarray.getJSONObject(i).getString("product_id"));
                                temp.setQty(resultsarray.getJSONObject(i).getString("qty"));
                                temp.setCurrency_symbol(resultsarray.getJSONObject(i).getString("currency_symbol"));
                                temp.setDescription(resultsarray.getJSONObject(i).getString("description"));
                                temp.setStatus(resultsarray.getJSONObject(i).getString("status"));
                                temp.setRating(resultsarray.getJSONObject(i).getString("rating"));
                                temp.setParent_category_id(resultsarray.getJSONObject(i).getString("parent_category_id"));
                                temp.setSuffix(resultsarray.getJSONObject(i).getString("suffix"));
                                temp.setOn_sale(resultsarray.getJSONObject(i).getString("on_sale"));
                                temp.setRegular_price(resultsarray.getJSONObject(i).getString("regular_price"));
                                product_models.add(temp);

                            }
                            dialog.dismiss();
                            mainProductAdapter = new SubProductAdapter(getApplicationContext(), product_models);
                            rv_sub_product_list.setAdapter(mainProductAdapter);
                        } catch (JSONException e) {
                            dialog.dismiss();
                            e.printStackTrace();
                        }

                    }

                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "No Internet connection", Toast.LENGTH_SHORT).show();
        }
    }

}
