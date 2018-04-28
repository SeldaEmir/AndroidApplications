package com.example.selda.myfoodnotes.model;


public class PlacesModel {

    int places_id;
    String places_image_path;
    String places_name;
    String places_loc_lang;
    String places_loc_long;
    String places_like_dislike;

    public PlacesModel() {
    }

    public PlacesModel(int places_id, String places_image_path, String places_name, String places_loc_lang, String places_loc_long, String places_like_dislike) {
        this.places_id = places_id;
        this.places_image_path = places_image_path;
        this.places_name = places_name;
        this.places_loc_lang = places_loc_lang;
        this.places_loc_long = places_loc_long;
        this.places_like_dislike = places_like_dislike;
    }

    public PlacesModel(String places_image_path, String places_name, String places_loc_lang, String places_loc_long, String places_like_dislike) {
        this.places_image_path = places_image_path;
        this.places_name = places_name;
        this.places_loc_lang = places_loc_lang;
        this.places_loc_long = places_loc_long;
        this.places_like_dislike = places_like_dislike;
    }

    public int getPlaces_id() {
        return places_id;
    }

    public void setPlaces_id(int places_id) {
        this.places_id = places_id;
    }

    public String getPlaces_image_path() {
        return places_image_path;
    }

    public void setPlaces_image_path(String places_image_path) {
        this.places_image_path = places_image_path;
    }

    public String getPlaces_name() {
        return places_name;
    }

    public void setPlaces_name(String places_name) {
        this.places_name = places_name;
    }

    public String getPlaces_loc_lang() {
        return places_loc_lang;
    }

    public void setPlaces_loc_lang(String places_loc_lang) {
        this.places_loc_lang = places_loc_lang;
    }

    public String getPlaces_loc_long() {
        return places_loc_long;
    }

    public void setPlaces_loc_long(String places_loc_long) {
        this.places_loc_long = places_loc_long;
    }

    public String getPlaces_like_dislike() {
        return places_like_dislike;
    }

    public void setPlaces_like_dislike(String places_like_dislike) {
        this.places_like_dislike = places_like_dislike;
    }
}
