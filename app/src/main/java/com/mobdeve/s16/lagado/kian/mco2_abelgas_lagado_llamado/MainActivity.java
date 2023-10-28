package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static String TITLE_TAG = "TITLE";
    public static String IMAGE_TAG = "IMAGE";
    public static String DESC_TAG = "DESC";
    public static String RATING_TAG = "RATING";
    public static String YEAR_TAG = "YEAR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sample data
        List<Anime> sampleData = new ArrayList<>();
        sampleData.add(new Anime("Code Geass", R.drawable.codegeass, "Lelouch go skrt skrt brrrrt!", "10/10", "Anime"));
        sampleData.get(0).setDate("Oct 2006 - Jul 2007");

        sampleData.add(new Anime("Dr Stone", R.drawable.drstone, "Yay coca cola!", "9.1/10", "Anime"));
        sampleData.get(1).setDate("Jul 2019 - Dec 2019");

        sampleData.add(new Anime("Oyasumi Punpun", R.drawable.punpun, "Free gallons of depresso espresso", "9.01/10", "Manga"));
        sampleData.get(2).setDate("Mar 2007 - Nov 2013");
        // ... add more data

        // Setting the adapter
        AnimeAdapter adapter = new AnimeAdapter(this, sampleData);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);  // Initialize the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Set its layout manager
        recyclerView.setAdapter(adapter);                             // Attach the adapter


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

        adapter.setOnItemClickListener(new AnimeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent detailIntent = new Intent(MainActivity.this, DetailActivity.class);
                // If needed, pass extra data to the DetailActivity using putExtra
                // TODO: might not need these extras in next phase since we just do API calls
                detailIntent.putExtra(TITLE_TAG, sampleData.get(position).getTitle());
                detailIntent.putExtra(IMAGE_TAG, sampleData.get(position).getThumbnail());
                detailIntent.putExtra(DESC_TAG, sampleData.get(position).getSynopsis());
                detailIntent.putExtra(RATING_TAG, sampleData.get(position).getRating());
                detailIntent.putExtra(YEAR_TAG, sampleData.get(position).getDate());
                startActivity(detailIntent);
            }
        });
    }
}

