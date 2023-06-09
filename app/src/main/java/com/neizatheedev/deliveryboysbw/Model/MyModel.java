package com.neizatheedev.deliveryboysbw.Model;


/**
 * @author Monei Bakang Motshegwe
 */

public class MyModel {

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    String name;
    String description;
    String date;
    String address;
    String owner;
    String userId;
    String site;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    String status;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    String productId;
    String image;
    String latitude;
    String longitude;
    String price;

    public MyModel(String productId, String name, String description, String date, String address, String owner, String userId, String site, String image, String latitude, String longitude, String price, String status) {
        this.productId = productId;
        this.status = status;
        this.name = name;
        this.description = description;
        this.date = date;
        this.address = address;
        this.owner = owner;
        this.userId = userId;
        this.site = site;
        this.image = image;
        this.latitude = latitude;
        this.longitude = longitude;
        this.price = price;
    }
}
