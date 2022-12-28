package com.example.ass_maihula;

public class ModelClass {
    private String id;
    private String Iname;
    private String qty,price,tprice;

    ModelClass(String id,String Iname,String qty,String price,String tprice){
        this.id = id;
        this.Iname = Iname;
        this.qty = qty;
        this.price = price;
        this.tprice = tprice;
    }

    public String getId() {
        return id;
    }

    public String getIname() {
        return Iname;
    }

    public String getQty() {
        return qty;
    }

    public String getPrice() {
        return price;
    }

    public String getTprice() {
        return tprice;
    }
}
