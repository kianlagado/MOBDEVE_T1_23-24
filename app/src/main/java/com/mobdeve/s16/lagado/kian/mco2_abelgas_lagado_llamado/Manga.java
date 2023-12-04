package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

public class Manga {
    private int mal_id, chapters;
    private String imageUrl, title, status, synopsis, date, authors, genres, score;
    private String userProgress, userScore, userStatus;

    // Constructor
    public Manga(int mal_id, String imageUrl, String title, int chapters, String status,
                 String score, String synopsis, String date,String authors, String genres) {
        this.mal_id = mal_id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.chapters = chapters;
        this.status = status;
        this.score = score;
        this.synopsis = synopsis;
        this.date = date;
        this.authors = authors;
        this.genres = genres;
    }

    // Getters
    public int getMal_id() { return mal_id; }
    public String getImageUrl() { return imageUrl; }
    public String getTitle() { return title; }
    public int getChapters() { return chapters; }
    public String getStatus() { return status; }
    public String getScore() { return score; }
    public String getSynopsis() { return synopsis; }
    public String getDate() {return date;}
    public String getAuthors() { return authors; }
    public String getGenres() { return genres; }
    public String getUserRating() {return userScore;}
    public String getUserProgress() {return userProgress;}
    public String getUserStatus() {return userStatus;}

    // Setters
    public void setMal_id(int mal_id) { this.mal_id = mal_id; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public void setTitle(String title) { this.title = title; }
    public void setChapters(int chapters) { this.chapters = chapters; }
    public void setStatus(String status) { this.status = status; }
    public void setScore(String score) { this.score = score; }
    public void setSynopsis(String synopsis) { this.synopsis = synopsis; }
    public void setAuthors(String authors) { this.authors = authors; }
    public void setGenres(String genres) { this.genres = genres; }
    public void setDate(String genres) { this.date = date; }
    public void setUserRating(String userScore) {this.userScore = userScore;}
    public void setUserProgress(String userProgress) {this.userProgress = userProgress;}
    public void setUserStatus(String userStatus) {this.userStatus = userStatus;}
}