package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import android.widget.ImageView;
import android.widget.TextView;
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

public class SearchActivity<T> extends AppCompatActivity {
    public static String TITLE_TAG = "TITLE";
    public static String IMAGE_TAG = "IMAGE";
    public static String DESC_TAG = "DESC";
    public static String RATING_TAG = "RATING";
    public static String DATE_TAG = "DATE";
    public static String STATUS_TAG = "STATUS";
    public static String GENRES_TAG = "GENRES";
    public static String STUDIOS_TAG = "STUDIOS";
    public static String AUTHORS_TAG = "AUTHORS";
    public static String EPISODES_TAG = "EPISODES";
    public static String CHAPTERS_TAG = "CHAPTERS";
    public static String TYPE_TAG = "TYPE";

    private RecyclerView searchRecyclerView;
    SearchResultAdapter searchResultAdapter;
    private SearchView searchView;
    private DataService animaExpress;
    private ArrayList<T> searchResults;
    TextView searchHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Intent Extras
        Intent i = getIntent();
        String type = i.getStringExtra(MainActivity.TYPE_TAG);

        // Default Values
        searchResults = new ArrayList<>();
        animaExpress = new DataService(SearchActivity.this);

        searchRecyclerView = findViewById(R.id.recycler_search);

        //Search Header
        searchHeader = findViewById(R.id.search_header);
        searchHeader.setText(type + " Search");

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
                if (query.isEmpty()) searchResults.clear();
                else searchJikan(animaExpress, type, query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query){
                if(query.isEmpty()){
                    searchResults.clear();
                }
                return true;
            }
        });

    }

    private void searchJikan(DataService animaExpress, String type, String query) {
        animaExpress.searchJikan(query, type, new DataService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(SearchActivity.this, "Failed to search", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(ArrayList response) {
                searchResults.addAll(response);

                // Initialize the RecyclerView and Adapter
                searchResultAdapter = new SearchResultAdapter(SearchActivity.this, searchResults, type);
                searchRecyclerView.setAdapter(searchResultAdapter);
                searchRecyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));

                searchResultAdapter.setOnItemClickListener(new SearchResultAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent detailIntent = new Intent(SearchActivity.this, DetailActivity.class);
                        // If needed, pass extra data to the DetailActivity using putExtra
                        T data = searchResults.get(position);
                        Toast.makeText(SearchActivity.this, data.toString(), Toast.LENGTH_SHORT).show();

                        if (type.equals("Anime")) {
                            TestAnime currAnime = (TestAnime) data;
                            detailIntent.putExtra(TITLE_TAG, currAnime.getTitle());
                            detailIntent.putExtra(IMAGE_TAG, currAnime.getImageUrl());
                            detailIntent.putExtra(RATING_TAG, currAnime.getScore());
                            detailIntent.putExtra(DESC_TAG, currAnime.getSynopsis());
                            detailIntent.putExtra(GENRES_TAG, currAnime.getGenres());
                            detailIntent.putExtra(EPISODES_TAG, currAnime.getEpisodes());
                            detailIntent.putExtra(DATE_TAG, currAnime.getDate());
                            detailIntent.putExtra(STUDIOS_TAG, currAnime.getStudios());
                            detailIntent.putExtra(STATUS_TAG, currAnime.getStatus());
                        }
                        else if (type.equals("Manga")) {
                            Manga currManga = (Manga) data;
                            detailIntent.putExtra(TITLE_TAG, currManga.getTitle());
                            detailIntent.putExtra(IMAGE_TAG, currManga.getImageUrl());
                            detailIntent.putExtra(RATING_TAG, currManga.getScore());
                            detailIntent.putExtra(DESC_TAG, currManga.getSynopsis());
                            detailIntent.putExtra(GENRES_TAG, currManga.getGenres());
                            detailIntent.putExtra(CHAPTERS_TAG, currManga.getChapters());
                            detailIntent.putExtra(DATE_TAG, currManga.getDate());
                            detailIntent.putExtra(AUTHORS_TAG, currManga.getAuthors());
                            detailIntent.putExtra(STATUS_TAG, currManga.getStatus());
                        }
                        detailIntent.putExtra(TYPE_TAG, type);
                        startActivity(detailIntent);
                    }
                });
            }
        });
    }

}
