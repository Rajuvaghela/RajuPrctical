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
import com.rajuprctical.Model.ProductModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    ProgressDialog dialog;
    RecyclerView rv_product_list;
    ArrayList <ProductModel> product_models=new ArrayList<>();
    MainProductAdapter mainProductAdapter;

    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading..");
        dialog.setCancelable(false);
        rv_product_list=(RecyclerView)findViewById(R.id.rv_product_list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        rv_product_list.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
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
          //  params.add("l_id", "");

            client.post( "http://phpjoomlacoders.com/ios/?webservice=1&vootouchservice=get_category", params, new TextHttpResponseHandler() {
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
                            JSONArray resultsarray = jsonObj.getJSONArray("product_categories");
                            for (int i = 0; i < resultsarray.length(); i++) {
                                ProductModel temp=new ProductModel();
                                temp.setImage(resultsarray.getJSONObject(i).getString("image"));
                                temp.setCategory_id(resultsarray.getJSONObject(i).getString("category_id"));
                                temp.setCategory_name(resultsarray.getJSONObject(i).getString("category_name"));
                                product_models.add(temp);

                            }
                            dialog.dismiss();
                            mainProductAdapter = new MainProductAdapter(getApplicationContext(), product_models);
                            rv_product_list.setAdapter(mainProductAdapter);
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
