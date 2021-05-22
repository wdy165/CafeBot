package com.example.myapplication;

public class DataPage {
    int image;
    String cafeName;
    String menuName;
    String price;

    public DataPage(int image, String menuName, String cafeName, String price){
        this.image = image;
        this.menuName = menuName;
        this.cafeName = cafeName;
        this.price = price;
    }

    public int getImage(){
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getMenuName(){
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getCafeName() {
        return cafeName;
    }

    public void setCafeName(String cafeName) {
        this.cafeName = cafeName;
    }

    public String getPrice(){
        return price;
    }

    public void setPrice(String price){
        this.price = price;
    }
}
