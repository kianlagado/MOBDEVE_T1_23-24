package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

public class Search {
    private String title;
    private String thumbnail_url;
    private String rating;

    public Search(String thumbnail, String title, String rating) {
        this.title = title;
        this.thumbnail_url = thumbnail;
        this.rating = rating;
    }

    public String getThumbnailUrl() {
        return thumbnail_url;
    }

    public String getTitle() {
        return title;
    }

    public String getRating() {
        return rating;
    }
}
