package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView searchRecyclerView;
    private SearchResultAdapter searchResultAdapter;
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Initialize the RecyclerView and Adapter
        searchRecyclerView = findViewById(R.id.recycler_search);
        List<Search> searchResults = new ArrayList<Search>();
        searchResultAdapter = new SearchResultAdapter(this, searchResults);
        searchRecyclerView.setAdapter(searchResultAdapter);
        searchRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Close button "X"
        ImageButton btnCloseSearch = findViewById(R.id.btn_close_search);
        btnCloseSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        searchView = findViewById(R.id.searchAnime);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query){
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query){
                if(query.isEmpty()){
                    searchResults.clear();
                }
                else{
                    RequestQueue queue = Volley.newRequestQueue(SearchActivity.this);
                    String url = "https://api.jikan.moe/v4/anime?q=api.jikan.moe/v4/anime?q="+query+"&sfw";

                    JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            String toShow = "Got nothing...";


                            try{
                                JSONArray data = response.getJSONArray("data");
                                searchResults.clear();

                                for(int i = 0; i < data.length(); i++){
                                    JSONObject arr = (JSONObject) data.get(i);

                                    String title = arr.getString("title");
                                    String rating = arr.getString("score");
                                    String thumbnail_url = arr  .getJSONObject("images")
                                                                .getJSONObject("jpg")
                                                                .getString("image_url");

                                    searchResults.add(new Search(thumbnail_url, title, rating));
                                }

                                toShow = String.valueOf(searchResults.size());
                            }
                            catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
                    queue.add(req);
                }
                return true;
            }
        });
    }

    private List<Search> getSearchResults() {
        List<Search> results = new ArrayList<>();
//        results.add(new Search(R.drawable.thumbnail1, "Stein's Gate", "10/10"));
//        results.add(new Search(R.drawable.thumbnail, "Attack on Titan", "10/10"));
        // ... add more data as needed
        return results;
    }
}
