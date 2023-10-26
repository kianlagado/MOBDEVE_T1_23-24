package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

public class Search {
    private String title;
    private int thumbnail; // Assuming you're using drawable resources for images
    private String rating;

    public Search(int thumbnail, String title, String rating) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.rating = rating;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public String getRating() {
        return rating;
    }
}
