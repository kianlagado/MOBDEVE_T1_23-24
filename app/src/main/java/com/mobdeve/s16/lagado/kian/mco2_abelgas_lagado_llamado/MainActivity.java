package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado.Anime;
import com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado.AnimeAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sample data
        List<Anime> sampleData = new ArrayList<>();
        sampleData.add(new Anime("Code Geass", R.drawable.codegeass, "Goated Anime frfr", "10/10"));
        sampleData.add(new Anime("Dr Stone", R.drawable.drstone, "This show is insane wtf", "9.1/10"));
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
                startActivity(detailIntent);
            }
        });
    }
}

