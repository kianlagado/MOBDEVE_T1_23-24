package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class TestAnime {

    // year: Assuming year of release or airing
    private int mal_id, episodes;

    // imageUrl: Assuming the main image URL
    // studios: This could be a list or a comma-separated string
    // genres: Similarly, a list or a comma-separated string
    private String imageUrl, title, status, synopsis, date, studios, genres, score;


    // Constructor
    public TestAnime(int mal_id, String imageUrl, String title, int episodes, String status,
                 String score, String synopsis, String date, String studios, String genres) {
        this.mal_id = mal_id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.episodes = episodes;
        this.status = status;
        this.score = score;
        this.synopsis = synopsis;
        this.date = date;
        this.studios = studios;
        this.genres = genres;
    }

    // Getters
    public int getMal_id() { return mal_id; }
    public String getImageUrl() { return imageUrl; }
    public String getTitle() { return title; }
    public int getEpisodes() { return episodes; }
    public String getStatus() { return status; }
    public String getScore() { return score; }
    public String getSynopsis() { return synopsis; }
    public String getStudios() { return studios; }
    public String getGenres() { return genres; }
    public String getDate() {return date;}


    // Setters
    public void setMal_id(int mal_id) { this.mal_id = mal_id; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public void setTitle(String title) { this.title = title; }
    public void setEpisodes(int episodes) { this.episodes = episodes; }
    public void setStatus(String status) { this.status = status; }
    public void setScore(String score) { this.score = score; }
    public void setSynopsis(String synopsis) { this.synopsis = synopsis; }
    public void setStudios(String studios) { this.studios = studios; }
    public void setGenres(String genres) { this.genres = genres; }
    public void setDate(String date) { this.date = date; }
}
