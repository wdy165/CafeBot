package com.example.myapplication;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderPage extends RecyclerView.ViewHolder {

    private ConstraintLayout layout;
    private ImageView image;
    private TextView cafeName;
    private TextView menuName;
    private TextView price;

    DataPage data;

    ViewHolderPage(View itemView) {
        super(itemView);
        cafeName = itemView.findViewById(R.id.cafe_name);
        layout = itemView.findViewById(R.id.layout);
        image = itemView.findViewById(R.id.sample_image);
        price = itemView.findViewById(R.id.product_price);
        menuName = itemView.findViewById(R.id.menu_name);
    }

    public void onBind(DataPage data){
        this.data = data;

        menuName.setText(data.getMenuName());
        cafeName.setText(data.getCafeName());
        image.setImageResource(data.getImage());
        price.setText(Integer.toString(data.getPrice()).concat("원"));
    }
}