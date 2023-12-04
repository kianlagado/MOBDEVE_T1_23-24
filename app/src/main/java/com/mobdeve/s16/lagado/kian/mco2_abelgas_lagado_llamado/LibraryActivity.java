package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;


import java.util.ArrayList;
import java.util.List;

public class LibraryActivity<T> extends AppCompatActivity {

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
    RecyclerView recyclerView;
    private String currentType;
    private String currentStatus;
    private DatabaseHelper databaseHelper;
    private ArrayList<T> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        // Default settings
        currentType = "Anime";
        currentStatus = "All";
        dataList = new ArrayList<>();
        databaseHelper = new DatabaseHelper(LibraryActivity.this);

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.library_recycler);

        // get initial data
        getList(currentType);

        // ----- entries count ------
        entriesCount = findViewById(R.id.entries_count);
        entriesCount.setText(dataList.size() + " entries");


        // ------ profile button ------
        profileIcon = findViewById(R.id.library_profile_icon);
        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(LibraryActivity.this, ProfileActivity.class);
                startActivity(profileIntent);
            }
        });

        // ---- filter and search buttons ----
        filterIcon = findViewById(R.id.library_filter_icon);
        filterIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent filterIntent = new Intent(LibraryActivity.this, FilterSortActivity.class);
                startActivity(filterIntent);
            }
        });

        searchIcon = findViewById(R.id.library_search_icon);
        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchIntent = new Intent(LibraryActivity.this, SearchActivity.class);
                startActivity(searchIntent);
            }
        });

        // ---- switch between anime and manga lists ----
        toggleList = findViewById(R.id.toggle_list);
        toggleList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!toggleList.isChecked()) {
                    planStatus.setText("Plan to Watch");
                    watchingStatus.setText("Watching");
                    currentType = "Anime";
                    if (currentStatus.equals("Reading")) currentStatus = "Watching";
                    if (currentStatus.equals("Plan to Read")) currentStatus = "Plan to Watch";
                }
                else {
                    planStatus.setText("Plan to Read");
                    watchingStatus.setText("Reading");
                    currentType = "Manga";
                    if (currentStatus.equals("Watching")) currentStatus = "Reading";
                    if (currentStatus.equals("Plan to Watch")) currentStatus = "Plan to Read";
                }
                getList(currentType);
                entriesCount.setText(dataList.size() + " entries");
                // Reset adapter
                setupAdapter();
            }
        });

        //For filtering recyclerview based on watch status and entry type
        // TODO: highlight currently selected status


        // ---- status buttons ----
        allStatus = findViewById(R.id.all_status);
//        allStatus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                currentStatus = "All";
//                currentEntries = filterListByStatus(currentStatus, currentType, sampleEntries);
//                libraryAdapter.updateEntries(currentEntries);
//                entriesCount.setText(currentEntries.size() + " entries");
//            }
//        });
        watchingStatus = findViewById(R.id.watching_status);
//        watchingStatus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (currentType.equals("Anime"))
//                    currentStatus = "Watching";
//                else
//                    currentStatus = "Reading";
//                currentEntries = filterListByStatus(currentStatus, currentType, sampleEntries);
//                libraryAdapter.updateEntries(currentEntries);
//                entriesCount.setText(currentEntries.size() + " entries");
//            }
//        });

        doneStatus = findViewById(R.id.completed_status);
//        doneStatus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                currentStatus = "Completed";
//                currentEntries = filterListByStatus(currentStatus, currentType, sampleEntries);
//                libraryAdapter.updateEntries(currentEntries);
//                entriesCount.setText(currentEntries.size() + " entries");
//            }
//        });

        planStatus = findViewById(R.id.planned_status);
//        planStatus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (currentType.equals("Anime"))
//                    currentStatus = "Plan to Watch";
//                else
//                    currentStatus = "Plan to Read";
//                currentEntries = filterListByStatus(currentStatus, currentType, sampleEntries);
//                libraryAdapter.updateEntries(currentEntries);
//                entriesCount.setText(currentEntries.size() + " entries");
//            }
//        });

        holdStatus = findViewById(R.id.hold_status);
//        holdStatus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                currentStatus = "On Hold";
//                currentEntries = filterListByStatus(currentStatus, currentType, sampleEntries);
//                libraryAdapter.updateEntries(currentEntries);
//                entriesCount.setText(currentEntries.size() + " entries");
//            }
//        });

        // ---- initial adapter setup ----
        setupAdapter();

    }



    private void setupAdapter() {
        libraryAdapter = new LibraryAdapter(LibraryActivity.this, dataList, currentType);
        recyclerView.setLayoutManager(new LinearLayoutManager(LibraryActivity.this)); // Set its layout manager
        recyclerView.setAdapter(libraryAdapter);                      // Attach the adapter
    }

    private void getList(String currentType) {
        dataList.clear();
        if (currentType.equals("Anime"))
            dataList.addAll(databaseHelper.getAllAnime());
        else if (currentType.equals("Manga"))
            dataList.addAll(databaseHelper.getAllManga());
    }

}