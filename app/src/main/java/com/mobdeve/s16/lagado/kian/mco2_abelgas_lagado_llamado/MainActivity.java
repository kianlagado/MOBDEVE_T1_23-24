package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static String TITLE_TAG = "TITLE";
    public static String IMAGE_TAG = "IMAGE";
    public static String DESC_TAG = "DESC";
    public static String RATING_TAG = "RATING";
    public static String YEAR_TAG = "YEAR";
    private String currentType;
    private List<TestAnime> currentEntries;
    private ArrayList<TestAnime> receivedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        receivedData = new ArrayList<>();
        //RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        DataService animaExpress = new DataService(MainActivity.this);

        animaExpress.displayTopAnime(new DataService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(MainActivity.this, "Failed to obtain Top Anime", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(ArrayList<TestAnime> response) {
                receivedData = response;

                // Default settings
                currentType = "Anime";

                // Setting the adapter
                AnimeAdapter adapter = new AnimeAdapter(MainActivity.this, receivedData);
                RecyclerView recyclerView = findViewById(R.id.recycler_view);  // Initialize the RecyclerView
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this)); // Set its layout manager
                recyclerView.setAdapter(adapter);                             // Attach the adapter

                // For highlighting and displaying filtered data based on currently selected mode
                LinearLayout toggleLayout = findViewById(R.id.toggle_layout);
                List<Button> toggleButtons = new ArrayList<>();
                for (int i = 0; i < toggleLayout.getChildCount(); i++) {
                    Button button = (Button) toggleLayout.getChildAt(i);
                    toggleButtons.add(button);

                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (Button item : toggleButtons) {
                                item.setBackgroundColor(getResources().getColor(R.color.edit_status_btn));
                                item.setActivated(false);
                            }

                            button.setActivated(true);
                            button.setBackgroundColor(getResources().getColor(R.color.selected_status_btn));
                            currentType = button.getText().toString();
                            //currentEntries = filterListByType(currentType, sampleData);
                            adapter.updateData(currentEntries);
                        }
                    });
                }


                adapter.setOnItemClickListener(new AnimeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent detailIntent = new Intent(MainActivity.this, DetailActivity.class);
                        // If needed, pass extra data to the DetailActivity using putExtra
                        // TODO: might not need these extras in next phase since we just do API calls
                        detailIntent.putExtra(TITLE_TAG, receivedData.get(position).getTitle());
                        detailIntent.putExtra(IMAGE_TAG, receivedData.get(position).getImageUrl());
                        detailIntent.putExtra(DESC_TAG, receivedData.get(position).getSynopsis());
                        detailIntent.putExtra(RATING_TAG, receivedData.get(position).getScore());
                        detailIntent.putExtra(YEAR_TAG, receivedData.get(position).getDate());
                        startActivity(detailIntent);
                    }
                });
            }
        });

        // Navbar Buttons

        ImageButton libraryIcon = findViewById(R.id.library_icon);
        libraryIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent libraryIntent = new Intent(MainActivity.this, LibraryActivity.class);
                startActivity(libraryIntent);
            }
        });

        ImageButton searchIcon = findViewById(R.id.search_icon);
        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchIntent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(searchIntent);
            }
        });

        ImageButton filterIcon = findViewById(R.id.filter_icon);
        filterIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent filterIntent = new Intent(MainActivity.this, FilterSortActivity.class);
                startActivity(filterIntent);
            }
        });

        ImageButton profileIcon = findViewById(R.id.profile_icon);
        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(profileIntent);
            }
        });


    }


//    private List<TestAnime> filterAnime(List<TestAnime> items) {
//        List<TestAnime> filteredList = new ArrayList<>();
//        for (int i = 0; i < items.size(); i++) {
//            TestAnime item = items.get(i);
//            if (item.getType().equals(type)) {
//                filteredList.add(item);
//            }
//        }
//        return filteredList;
//    }
}

