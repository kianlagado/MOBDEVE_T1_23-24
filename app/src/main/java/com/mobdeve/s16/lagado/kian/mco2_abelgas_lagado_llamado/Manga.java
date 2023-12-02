package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

public class Manga {
    private int mal_id;
    private String imageUrl; // Assuming the main image URL
    private String title;
    private int chapters;
    private String status;
    private double score;
    private String synopsis;
    private String authors; // This could be a list or a comma-separated string
    private String genres; // Similarly, a list or a comma-separated string

    // Constructor
    public Manga(int mal_id, String imageUrl, String title, int chapters, String status,
                 double score, String synopsis, String authors, String genres) {
        this.mal_id = mal_id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.chapters = chapters;
        this.status = status;
        this.score = score;
        this.synopsis = synopsis;
        this.authors = authors;
        this.genres = genres;
    }

    // Getters
    public int getMal_id() { return mal_id; }
    public String getImageUrl() { return imageUrl; }
    public String getTitle() { return title; }
    public int getChapters() { return chapters; }
    public String getStatus() { return status; }
    public double getScore() { return score; }
    public String getSynopsis() { return synopsis; }
    public String getAuthors() { return authors; }
    public String getGenres() { return genres; }

    // Setters
    public void setMal_id(int mal_id) { this.mal_id = mal_id; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public void setTitle(String title) { this.title = title; }
    public void setChapters(int chapters) { this.chapters = chapters; }
    public void setStatus(String status) { this.status = status; }
    public void setScore(double score) { this.score = score; }
    public void setSynopsis(String synopsis) { this.synopsis = synopsis; }
    public void setAuthors(String authors) { this.authors = authors; }
    public void setGenres(String genres) { this.genres = genres; }
}