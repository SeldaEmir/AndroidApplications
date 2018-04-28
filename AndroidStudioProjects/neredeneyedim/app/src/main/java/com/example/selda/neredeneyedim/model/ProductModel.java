package com.example.selda.neredeneyedim.model;

/**
 * Created by Lab08-ogretmen on 12.04.2018.
 */

public class ProductModel {

    int product_id;
    int places_id;
    String product_date;
    String product_image_path;
    String product_name;
    String product_price;
    String product_like_dislike;

    public ProductModel() {
    }

    public String getProduct_date() {
        return product_date;
    }

    public void setProduct_date(String product_date) {
        this.product_date = product_date;
    }

    public ProductModel(int product_id, int places_id, String product_date, String product_image_path, String product_name, String product_price, String product_like_dislike) {
        this.product_id = product_id;
        this.places_id = places_id;
        this.product_date = product_date;
        this.product_image_path = product_image_path;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_like_dislike = product_like_dislike;
    }

    public ProductModel(int places_id, String product_date, String product_image_path, String product_name, String product_price, String product_like_dislike) {
        this.places_id = places_id;
        this.product_date = product_date;
        this.product_image_path = product_image_path;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_like_dislike = product_like_dislike;
    }

    public String getProduct_like_dislike() {
        return product_like_dislike;
    }

    public void setProduct_like_dislike(String product_like_dislike) {
        this.product_like_dislike = product_like_dislike;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getPlaces_id() {
        return places_id;
    }

    public void setPlaces_id(int places_id) {
        this.places_id = places_id;
    }



    public String getProduct_image_path() {
        return product_image_path;
    }

    public void setProduct_image_path(String product_image_path) {
        this.product_image_path = product_image_path;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }
}
