package com.example.selda.neredeneyedim.model;

/**
 * Created by Lab08-ogretmen on 12.04.2018.
 */

public class ProductCommentModel {
    int product_id;
    String comment_date;
    String comment_degree;
    String comment_content;

    public ProductCommentModel() {
    }

    public ProductCommentModel(int product_id, String comment_date, String comment_degree, String comment_content) {
        this.product_id = product_id;
        this.comment_date = comment_date;
        this.comment_degree = comment_degree;
        this.comment_content = comment_content;
    }

    public ProductCommentModel(String comment_date, String comment_degree, String comment_content) {
        this.comment_date = comment_date;
        this.comment_degree = comment_degree;
        this.comment_content = comment_content;
    }

    public String getComment_date() {
        return comment_date;
    }

    public void setComment_date(String comment_date) {
        this.comment_date = comment_date;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }



    public String getComment_degree() {
        return comment_degree;
    }

    public void setComment_degree(String comment_degree) {
        this.comment_degree = comment_degree;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }
}
