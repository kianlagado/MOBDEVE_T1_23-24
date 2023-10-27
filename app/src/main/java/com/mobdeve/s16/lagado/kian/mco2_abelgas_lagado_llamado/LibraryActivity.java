package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class LibraryActivity extends AppCompatActivity {

    // TODO: change toggle button to a better looking one
    ToggleButton toggleList;
    TextView entriesCount;
    TextView planStatus;
    ImageButton profileIcon;
    ImageButton filterIcon;
    ImageButton searchIcon;
    LibraryAdapter libraryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        toggleList = (ToggleButton) findViewById(R.id.toggle_list);
        planStatus = (TextView) findViewById(R.id.planned_status);
        profileIcon = (ImageButton) findViewById(R.id.library_profile_icon);
        filterIcon = (ImageButton) findViewById(R.id.library_filter_icon);
        searchIcon = (ImageButton) findViewById(R.id.library_search_icon);
        entriesCount = (TextView) findViewById(R.id.entries_count);

        // Sample library entries
        List<Anime> sampleEntries = new ArrayList<>();
        sampleEntries.add(new Anime("Code Geass", R.drawable.codegeass, "Goated Anime frfr", "10/10"));
        sampleEntries.get(0).setUserStatus("Plan");
        sampleEntries.get(0).setYear("2006");
        sampleEntries.get(0).setUserProgress("0/25");
        sampleEntries.get(0).setUserRating("-");

        sampleEntries.add(new Anime("Dr Stone", R.drawable.drstone, "This show is insane wtf", "9.1/10"));
        sampleEntries.get(1).setUserStatus("Completed");
        sampleEntries.get(1).setYear("2019");
        sampleEntries.get(1).setUserProgress("24/24");
        sampleEntries.get(1).setUserRating("10");

        // Library adapter
        RecyclerView recyclerView = findViewById(R.id.library_recycler);  // Initialize the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Set its layout manager
        libraryAdapter = new LibraryAdapter(this, sampleEntries);
        recyclerView.setAdapter(libraryAdapter);                             // Attach the adapter

        entriesCount.setText(sampleEntries.size() + " entries");

        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(LibraryActivity.this, ProfileActivity.class);
                startActivity(profileIntent);
            }
        });

        filterIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent filterIntent = new Intent(LibraryActivity.this, FilterSortActivity.class);
                startActivity(filterIntent);
            }
        });

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchIntent = new Intent(LibraryActivity.this, SearchActivity.class);
                startActivity(searchIntent);
            }
        });

        toggleList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!toggleList.isChecked()) {
                    planStatus.setText("Plan to Watch");
                }
                else {
                    planStatus.setText("Plan to Read");
                }
            }
        });

    }
}