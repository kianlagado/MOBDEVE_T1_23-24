package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataService<T> {

    public static final String QUERY_FOR_TOP_ANIME = "https://api.jikan.moe/v4/top/anime";
    public static final String QUERY_FOR_TOP_MANGA = "https://api.jikan.moe/v4/top/manga";
    Context context;

    ArrayList<TestAnime> animeList;
    ArrayList<Manga> mangaList;

    public DataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener<T> {
        void onError(String message);
        void onResponse(ArrayList<T> response);
    }


    // TODO: manga isnt working for some reason
    public void displayTop(String currentType, VolleyResponseListener volleyResponseListener) {

        animeList = new ArrayList<>();
        mangaList = new ArrayList<>();

        String url = "";
        if (currentType.equals("Anime"))
            url = QUERY_FOR_TOP_ANIME;
        else if (currentType.equals("Manga"))
            url = QUERY_FOR_TOP_MANGA;

        Toast.makeText(context, url, Toast.LENGTH_LONG).show();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray entries = response.getJSONArray("data");

                    for (int i=0; i < entries.length(); i++) {
                        JSONObject entry = entries.getJSONObject(i);

                        // base info
                        String imageUrl = entry.getJSONObject("images").getJSONObject("jpg").getString("image_url");
                        int ID = entry.getInt("mal_id");
                        String title = entry.getString("title");
                        String status = entry.getString("status");
                        String score = entry.getString("score") + "/10";
                        String synopsis = entry.getString("synopsis");

                        String genres = "";
                        JSONArray genresArr = entry.getJSONArray("genres");
                        for (int j = 0; j < genresArr.length(); j++) {
                            JSONObject genresObj = genresArr.getJSONObject(j);
                            genres += genresObj.getString("name");
                            if (j+1 != genresArr.length()) {
                                genres += ", ";
                            }
                        }

                        int episodes = 0;
                        String date = "";
                        String studios = "";
                        // anime info
                        if (currentType.equals("Anime")) {
                            episodes = entry.getInt("episodes");
                            date = entry.getJSONObject("aired").getString("string");
                            JSONArray studiosArr = entry.getJSONArray("studios");
                            for (int j = 0; j < studiosArr.length(); j++) {
                                JSONObject studiosObj = studiosArr.getJSONObject(j);
                                studios += studiosObj.getString("name");
                                if (j + 1 != studiosArr.length()) {
                                    studios += ", ";
                                }
                            }

                            TestAnime animeObject = new TestAnime(ID, imageUrl, title, episodes, status, score, synopsis, date, studios, genres);
                            animeList.add(animeObject);
                        }

                        int chapters = 0;
                        String authors = "";
                        if (currentType.equals("Manga")) { // manga info
                            chapters = entry.getInt("chapters");
                            date = entry.getJSONObject("published").getString("string");
                            JSONArray authorsArr = entry.getJSONArray("authors");
                            for (int j = 0; j < authorsArr.length(); j++) {
                                JSONObject studiosObj = authorsArr.getJSONObject(j);
                                authors += studiosObj.getString("name");
                                if (j + 1 != authorsArr.length()) {
                                    authors += ", ";
                                }
                            }

                            Manga mangaObject = new Manga(ID, imageUrl, title, chapters, status, score, synopsis, date, authors, genres);
                            mangaList.add(mangaObject);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (currentType.equals("Anime")) volleyResponseListener.onResponse(animeList);
                else if (currentType.equals("Manga")) volleyResponseListener.onResponse(mangaList);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error getting JsonObject", Toast.LENGTH_SHORT).show();
                volleyResponseListener.onError("Something went wrong!");
            }
        });

        DataSingleton.getInstance(context).addToRequestQueue(request);
    }

}
