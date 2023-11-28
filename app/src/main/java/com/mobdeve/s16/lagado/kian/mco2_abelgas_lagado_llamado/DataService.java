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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DataService {

    public static final String QUERY_FOR_TOP_ANIME = "https://api.jikan.moe/v4/top/anime";
    Context context;
    ArrayList<String> titles, synopsies, ratings;
    ArrayList<ArrayList<String>> dataList;

    public DataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);
        void onResponse(ArrayList<ArrayList<String>> response);
    }

    // TODO: WIP
    public void getTopAnime(VolleyResponseListener volleyResponseListener) {
        dataList = new ArrayList<>();
        titles = new ArrayList<>();
        synopsies = new ArrayList<>();
        ratings = new ArrayList<>();

        String url = QUERY_FOR_TOP_ANIME;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                //String ID = "";
                try {
                    JSONArray data = response.getJSONArray("data");

                    for (int i=0; i < data.length(); i++) {
                        JSONObject anime = data.getJSONObject(i);
                        //String image = anime.getJSONObject("images").getJSONObject("jpg").getString("image_url");
                        //ID = anime.getString("mal_id");
                        titles.add(anime.getString("title"));
                        synopsies.add(anime.getString("synopsis"));
                        ratings.add(anime.getString("score") + "/10");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                dataList.add(titles);
                dataList.add(synopsies);
                dataList.add(ratings);

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
