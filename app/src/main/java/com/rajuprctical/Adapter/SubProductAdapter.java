package com.rajuprctical.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rajuprctical.Model.ProductModel;
import com.rajuprctical.Model.SubProductModel;
import com.rajuprctical.R;
import com.rajuprctical.SubProductActivity;

import java.util.ArrayList;

/**
 * Created by Shailesh on 14/03/18.
 */


public class SubProductAdapter extends RecyclerView.Adapter<SubProductAdapter.MyViewHolder> {

    ArrayList<SubProductModel> listuser=new ArrayList<>();
    Context context;
    public SubProductAdapter(Context context, ArrayList<SubProductModel> listuser) {
        this.context = context;
        this.listuser = listuser;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_main_product_detail, parent, false);
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tv_product_title.setText(listuser.get(position).getTitle());
        holder.tv_product_qty.setText(listuser.get(position).getQty());
        holder.tv_product_currency_symbol.setText(listuser.get(position).getCurrency_symbol());
        holder.tv_product_regular_price.setText(listuser.get(position).getRegular_price());
        holder.tv_product_title.setText(listuser.get(position).getTitle());
        Glide.with(context).load(listuser.get(position).getImage()).into(holder.iv_prodct_image);
        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listuser.get(position).getProduct_id() !=null){
                   /* Intent intent = new Intent(context, SubProductActivity.class);
                    intent.putExtra("product_id", listuser.get(position).getProduct_id());
                    context.startActivity(intent);*/
                }

            }
        });

    }


    @Override
    public int getItemCount() {
        return listuser.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_product_title,tv_product_currency_symbol,tv_product_description,tv_product_regular_price,tv_product_qty;
        ImageView iv_prodct_image;
        CardView card_view;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_product_title = (TextView) itemView.findViewById(R.id.tv_product_title);
            tv_product_qty = (TextView) itemView.findViewById(R.id.tv_product_qty);
            tv_product_currency_symbol = (TextView) itemView.findViewById(R.id.tv_product_currency_symbol);
            tv_product_description = (TextView) itemView.findViewById(R.id.tv_product_description);
            tv_product_regular_price = (TextView) itemView.findViewById(R.id.tv_product_regular_price);
            iv_prodct_image = (ImageView) itemView.findViewById(R.id.iv_prodct_image);
            card_view=(CardView)itemView.findViewById(R.id.card_view);

        }
    }
}


