<<<<<<< Updated upstream:java/com/mobdeve/s16/lagado/kian/mco2_abelgas_lagado_llamado/Anime.java
package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;
public class Anime {
    private String title;
    private int thumbnail; // Assuming you're using drawable resources for images
    private String synopsis;
    private String rating;

    public Anime(String title, int thumbnail, String synopsis, String rating) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.synopsis = synopsis;
        this.rating = rating;
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
=======
package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;
public class Anime {
    private String title;
    private int thumbnail; // Assuming you're using drawable resources for images
    private String synopsis;
    private String rating;
    private String status;

    public Anime(String title, int thumbnail, String synopsis, String rating) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.synopsis = synopsis;
        this.rating = rating;
        this.status = "";
    }

    public void setStatus(String s) {
        this.status = s;
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
    public String getStatus() {return status;}
>>>>>>> Stashed changes:app/src/main/java/com/mobdeve/s16/lagado/kian/mco2_abelgas_lagado_llamado/Anime.java
}