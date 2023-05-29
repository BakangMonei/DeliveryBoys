package com.neizatheedev.deliveryboysbw.Model;

import java.util.Objects;

public class Users {

    // Attributes
    String username, email, password, firstName, lastName, dateOfBirth, from, to, gender, omang, time, PriceDelivery, phone, product;
    String shop, pay, amount;

    // Super Constructor
    public Users(){
        super();
    }

    // Constructors
    public Users(String username, String email, String password, String firstName, String lastName, String dateOfBirth, String from, String to, String gender, String omang, String time, String priceDelivery, String phone, String product, String shop, String pay, String amount) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.from = from;
        this.to = to;
        this.gender = gender;
        this.omang = omang;
        this.time = time;
        PriceDelivery = priceDelivery;
        this.phone = phone;
        this.product = product;
        this.shop = shop;
        this.pay = pay;
        this.amount = amount;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getGender() {
        return gender;
    }

    public String getOmang() {
        return omang;
    }

    public String getTime() {
        return time;
    }

    public String getPriceDelivery() {
        return PriceDelivery;
    }

    public String getPhone() {
        return phone;
    }

    public String getProduct() {
        return product;
    }


    // Setters

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setOmang(String omang) {
        this.omang = omang;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPriceDelivery(String priceDelivery) {
        PriceDelivery = priceDelivery;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setProduct(String product) {
        this.product = product;
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

    // toString()

    @Override
    public String toString() {
        return "Users{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", gender='" + gender + '\'' +
                ", omang='" + omang + '\'' +
                ", time='" + time + '\'' +
                ", PriceDelivery='" + PriceDelivery + '\'' +
                ", phone='" + phone + '\'' +
                ", product='" + product + '\'' +
                ", shop='" + shop + '\'' +
                ", pay='" + pay + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }

    // equals() & hashCodes()

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return username.equals(users.username) && email.equals(users.email) && password.equals(users.password) && firstName.equals(users.firstName) && lastName.equals(users.lastName) && dateOfBirth.equals(users.dateOfBirth) && from.equals(users.from) && to.equals(users.to) && gender.equals(users.gender) && omang.equals(users.omang) && time.equals(users.time) && PriceDelivery.equals(users.PriceDelivery) && phone.equals(users.phone) && product.equals(users.product) && shop.equals(users.shop) && pay.equals(users.pay) && amount.equals(users.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, password, firstName, lastName, dateOfBirth, from, to, gender, omang, time, PriceDelivery, phone, product, shop, pay, amount);
    }
}
