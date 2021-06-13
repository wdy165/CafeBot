package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewHolderPage extends RecyclerView.ViewHolder {

    private ImageView image;
    private TextView cafeName;
    private TextView menuName;
    private TextView price;
    DataPage data;

    ViewHolderPage(View itemView) {
        super(itemView);

        cafeName = itemView.findViewById(R.id.cafe_name);
        image = itemView.findViewById(R.id.sample_image);
        price = itemView.findViewById(R.id.product_price);
        menuName = itemView.findViewById(R.id.menu_name);

        image.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String str = cafeName.getText().toString();
                Context context = v.getContext();

                Intent intent = new Intent(v.getContext(), MapActivity.class);
                intent.putExtra("cafeName", str);
                context.startActivity(intent);
            }
        });
    }
    public void onBind(DataPage data){
        this.data = data;

        menuName.setText(data.getMenuName());
        cafeName.setText(data.getCafeName());
        image.setImageResource(data.getImage());
        price.setText(Integer.toString(data.getPrice()).concat("원"));

    }
}