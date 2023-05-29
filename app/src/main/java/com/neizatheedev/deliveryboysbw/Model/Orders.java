package com.neizatheedev.deliveryboysbw.Model;

import java.util.Objects;

public class Orders {

    String shop, pay, amount;

    public Orders(){
    }

    public Orders(String shop, String pay, String amount){
        this.shop = shop;
        this.pay = pay;
        this.amount = amount;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return shop.equals(orders.shop) && pay.equals(orders.pay) && amount.equals(orders.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shop, pay, amount);
    }
    @Override
    public String toString() {
        return "Orders{" +
                "shop='" + shop + '\'' +
                ", pay='" + pay + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
