package com.example.myapplication;

public class DataPage implements Comparable<DataPage>  {
    int image;
    String menuName;
    String cafeName;
    String categoryName;
    int price;

    public DataPage(int image, String menuName, String cafeName, String categoryName, int price){
        this.image = image;
        this.menuName = menuName;
        this.cafeName = cafeName;
        this.categoryName = categoryName;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getPrice(){
        return price;
    }

    public void setPrice(int price){
        this.price = price;
    }

    @Override
    public int compareTo(DataPage o) {
        int targetPrice = o.getPrice();
        if(price == targetPrice) return 0;
        else if(price > targetPrice) return 1;
        else return -1;
    }
}
