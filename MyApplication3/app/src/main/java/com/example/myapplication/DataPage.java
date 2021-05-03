package com.example.myapplication;

public class DataPage {
    int image;
    String cafeName;
    int price;

    public DataPage(int image, String cafeName){
        this.image = image;
        this.cafeName = cafeName;
    }

    public DataPage(int image, String cafeName, int price){
        this.image = image;
        this.cafeName = cafeName;
        this.price = price;
    }

    public int getImage(){
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getCafeName() {
        return cafeName;
    }

    public void setCafeName(String cafeName) {
        this.cafeName = cafeName;
    }

    public int getPrice(){
        return price;
    }

    public void setPrice(int price){
        this.price = price;
    }
}
