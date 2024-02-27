package com.example.demo.entityFile.Events;

public class TicketingOption {
    private String tierName;
    private double tierPrice;
    private int tierQty;

    public TicketingOption(String tierName, double tierPrice, int tierQty){
        this.tierName = tierName;
        this.tierPrice = tierPrice;
        this.tierQty = tierQty;
    }

    public String getTierName(){
        return this.tierName;
    }

    public double getTierPrice(){
        return this.tierPrice;
    }

    public int getTierQty(){
        return this.tierQty;
    }
}
