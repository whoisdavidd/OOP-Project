package com.example.demo.requestBodies;

public class SellTicketsBody {
    private String tierName;
    private int tierQty;

    public String getTierName(){
        return this.tierName;
    }

    public int getTierQty(){
        return this.tierQty;
    }
}
