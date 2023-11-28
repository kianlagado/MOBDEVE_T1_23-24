package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TestAnime {
    private String title;
    private int thumbnail; // Assuming you're using drawable resources for images
    private String synopsis;
    private String rating;
    private String userRating;
    private String userStatus;
    private String userProgress;
    private String date;
    private String type;


    public TestAnime(String title, int thumbnail, String synopsis, String rating, String type) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.synopsis = synopsis;
        this.rating = rating;
        this.type = type;
    }
    public void setUserStatus(String s) {
        this.userStatus = s;
    }

    public void setUserProgress(String s) {
        this.userProgress = s;
    }

    public void setDate(String s) {
        this.date = s;
    }
    public void setUserRating(String s) {
        this.userRating = s;
    }

    public String getTitle() {
        return title;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getRating() {
        return rating;
    }
    public String getUserStatus() {return userStatus;}
    public String getDate() {return date;}
    public String getUserProgress() {return userProgress;}
    public String getUserRating() {return userRating;}
    public String getType() {return type;}

}