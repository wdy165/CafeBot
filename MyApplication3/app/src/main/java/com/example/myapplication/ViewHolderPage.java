package com.example.myapplication;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderPage extends RecyclerView.ViewHolder {

    private ConstraintLayout layout;

    private TextView cafeName;
    private ImageView image;
    private TextView price;

    DataPage data;

    ViewHolderPage(View itemView) {
        super(itemView);
        cafeName = itemView.findViewById(R.id.cafe_name);
        layout = itemView.findViewById(R.id.layout);
        image = itemView.findViewById(R.id.sample_image);
        price = itemView.findViewById(R.id.product_price);
    }

    public void onBind(DataPage data){
        this.data = data;

        cafeName.setText(data.getCafeName());
        image.setImageResource(data.getImage());
        price.setText(Integer.toString(data.getPrice()));
    }
}