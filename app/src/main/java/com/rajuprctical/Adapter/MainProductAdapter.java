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
import com.rajuprctical.R;
import com.rajuprctical.SubProductActivity;

import java.util.ArrayList;

/**
 * Created by Shailesh on 14/03/18.
 */


public class MainProductAdapter extends RecyclerView.Adapter<MainProductAdapter.MyViewHolder> {


    ArrayList<ProductModel> listuser=new ArrayList<>();
    Context context;

    public MainProductAdapter(Context context, ArrayList<ProductModel> listuser) {
        this.context = context;
        this.listuser = listuser;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_main_product, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // set the data in items

        holder.tv_product_name.setText(listuser.get(position).getCategory_name());
        Glide.with(context).load(listuser.get(position).getImage()).into(holder.iv_prodct_image);
        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listuser.get(position).getCategory_id() !=null){
                    Intent intent = new Intent(context, SubProductActivity.class);
                    intent.putExtra("product_id", listuser.get(position).getCategory_id());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }

            }
        });

    }


    @Override
    public int getItemCount() {
        return listuser.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView tv_product_name;
        ImageView iv_prodct_image;
        CardView card_view;

        public MyViewHolder(View itemView) {
            super(itemView);

            // get the reference of item view's
            tv_product_name = (TextView) itemView.findViewById(R.id.tv_product_name);
            iv_prodct_image = (ImageView) itemView.findViewById(R.id.iv_prodct_image);
            card_view=(CardView)itemView.findViewById(R.id.card_view);

        }
    }
}


