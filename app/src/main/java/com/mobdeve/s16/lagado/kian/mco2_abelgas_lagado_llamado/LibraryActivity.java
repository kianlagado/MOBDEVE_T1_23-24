package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class LibraryActivity extends AppCompatActivity {

    // TODO: change toggle button to a better looking one
    ToggleButton toggleList;
    TextView entriesCount;
    ImageButton profileIcon;
    ImageButton filterIcon;
    ImageButton searchIcon;
    Button planStatus;
    Button allStatus;
    Button watchingStatus;
    Button doneStatus;
    Button holdStatus;
    LibraryAdapter libraryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        toggleList = findViewById(R.id.toggle_list);
        profileIcon = findViewById(R.id.library_profile_icon);
        filterIcon = findViewById(R.id.library_filter_icon);
        searchIcon = findViewById(R.id.library_search_icon);
        entriesCount = findViewById(R.id.entries_count);

        planStatus = findViewById(R.id.planned_status);
        allStatus = findViewById(R.id.all_status);
        watchingStatus = findViewById(R.id.watching_status);
        doneStatus = findViewById(R.id.completed_status);
        holdStatus = findViewById(R.id.hold_status);

        // Sample library entries
        List<Anime> sampleEntries = new ArrayList<>();
        sampleEntries.add(new Anime("Code Geass", R.drawable.codegeass, "Lelouch go skrt skrt brrrrt!", "10/10", "Anime"));
        sampleEntries.get(0).setUserStatus("Plan to Watch");
        sampleEntries.get(0).setYear("2006");
        sampleEntries.get(0).setUserProgress("0/25");
        sampleEntries.get(0).setUserRating("-");

        sampleEntries.add(new Anime("Dr Stone", R.drawable.drstone, "Yay coca cola!", "9.1/10", "Anime"));
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

        //For filtering recyclerview based on watch status
        allStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                libraryAdapter.updateEntries(sampleEntries);
                entriesCount.setText(sampleEntries.size() + " entries");
            }
        });

        watchingStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Anime> watchingEntries = filterListByStatus("Watching", sampleEntries);
                libraryAdapter.updateEntries(watchingEntries);
                entriesCount.setText(watchingEntries.size() + " entries");
            }
        });

        doneStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Anime> completedEntries = filterListByStatus("Completed", sampleEntries);
                libraryAdapter.updateEntries(completedEntries);
                entriesCount.setText(completedEntries.size() + " entries");
            }
        });

        planStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Anime> planEntries = filterListByStatus("Plan to Watch", sampleEntries);
                libraryAdapter.updateEntries(planEntries);
                entriesCount.setText(planEntries.size() + " entries");
            }
        });

        holdStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Anime> holdEntries = filterListByStatus("On Hold", sampleEntries);
                libraryAdapter.updateEntries(holdEntries);
                entriesCount.setText(holdEntries.size() + " entries");
            }
        });

    }

    private List<Anime> filterListByStatus(String status, List<Anime> entries) {
        List<Anime> filteredList = new ArrayList<>();
        for (int i = 0; i < entries.size(); i++) {
            Anime item = entries.get(i);
            if (item.getUserStatus().equals(status) || "All".equals(status)) {
                filteredList.add(item);
            }
        }
        return filteredList;
    }
}