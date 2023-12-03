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

public class DataService {

    public static final String QUERY_FOR_TOP_ANIME = "https://api.jikan.moe/v4/top/anime";
    Context context;
    ArrayList<TestAnime> dataList;

    public DataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);
        void onResponse(ArrayList<TestAnime> response);
    }

    // TODO: WIP
    public void displayTopAnime(VolleyResponseListener volleyResponseListener) {
        dataList = new ArrayList<>();

        String url = QUERY_FOR_TOP_ANIME;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                //String ID = "";
                try {
                    JSONArray data = response.getJSONArray("data");

                    for (int i=0; i < data.length(); i++) {
                        JSONObject anime = data.getJSONObject(i);
                        String imageUrl = anime.getJSONObject("images").getJSONObject("jpg").getString("image_url");
                        int ID = anime.getInt("mal_id");
                        String title = anime.getString("title");
                        int episodes = anime.getInt("episodes");
                        String status = anime.getString("status");
                        String score = anime.getString("score") + "/10";
                        String synopsis = anime.getString("synopsis");
                        String date = anime.getJSONObject("aired").getString("string");

                        String studios = "";
                        JSONArray studiosArr = anime.getJSONArray("studios");
                        for (int j = 0; j < studiosArr.length(); j++) {
                            JSONObject studiosObj = studiosArr.getJSONObject(j);
                            studios += studiosObj.getString("name");
                            if (j+1 != studiosArr.length()) {
                                studios += ", ";
                            }
                        }

                        String genres = "";
                        JSONArray genresArr = anime.getJSONArray("studios");
                        for (int j = 0; j < genresArr.length(); j++) {
                            JSONObject genresObj = genresArr.getJSONObject(j);
                            genres += genresObj.getString("name");
                            if (j+1 != genresArr.length()) {
                                genres += ", ";
                            }
                        }


                        TestAnime animeObject = new TestAnime(ID, imageUrl, title, episodes, status, score, synopsis, date, studios, genres);
                        dataList.add(animeObject);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                volleyResponseListener.onResponse(dataList);

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

//    public List<ItemModel> getItemDetailsByID(String ID) {
//
//    }
//
//    public List<ItemModel> getItemDetailsByTitle(String title) {
//
//    }

}
