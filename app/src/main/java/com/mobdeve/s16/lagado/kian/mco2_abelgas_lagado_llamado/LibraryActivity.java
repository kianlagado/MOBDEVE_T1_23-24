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
    List<TestAnime> sampleEntries;
    LibraryAdapter libraryAdapter;
    private String currentType;
    private List<TestAnime> currentEntries;
    private String currentStatus;

    // For displaying changes made in editactivity on the library recyclerview
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        String action = result.getData().getStringExtra(EditEntryActivity.ACTION_TAG);
                        int itemIndex = result.getData().getIntExtra(EditEntryActivity.POS_TAG, 0);
                        if (action.equals("Confirm")) {
                            // Getting the index of entry that was updated and the data that was updated
                            String updated_rating = result.getData().getStringExtra(EditEntryActivity.RATING_TAG);
                            String updated_progress = result.getData().getStringExtra(EditEntryActivity.PROGRESS_TAG);
                            String updated_status = result.getData().getStringExtra(EditEntryActivity.STATUS_TAG);

                            // Updating the entry
                            currentEntries.get(itemIndex).setUserRating(updated_rating);
                            currentEntries.get(itemIndex).setUserProgress(updated_progress);
                            currentEntries.get(itemIndex).setUserStatus(updated_status);
                        }
                        else if (action.equals("Remove")) {
                            // Get title of removed entry and remove from dataset
                            String removed_entry_title = currentEntries.get(itemIndex).getTitle();
                            sampleEntries.removeIf(entry -> entry.getTitle().equals(removed_entry_title));
                        }

                        // Updating the dataset and refreshing recyclerview
                        currentEntries = filterListByStatus(currentStatus, currentType, sampleEntries);
                        entriesCount.setText(currentEntries.size() + " entries");
                        libraryAdapter.updateEntries(currentEntries);
                    }
                }
            }
    );

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
        // TODO: this should be entries from SQLite
        sampleEntries = new ArrayList<>();
        sampleEntries.add(new TestAnime("Code Geass", R.drawable.codegeass, "Lelouch go skrt skrt brrrrt!", "10/10", "Anime"));
        sampleEntries.get(0).setUserStatus("Plan to Watch");
        sampleEntries.get(0).setDate("Oct 2006 - Jul 2007");
        sampleEntries.get(0).setUserProgress("0");
        sampleEntries.get(0).setUserRating("-");

        sampleEntries.add(new TestAnime("Dr Stone", R.drawable.drstone, "Yay coca cola!", "9.1/10", "Anime"));
        sampleEntries.get(1).setUserStatus("Completed");
        sampleEntries.get(1).setDate("Jul 2019 - Dec 2019");
        sampleEntries.get(1).setUserProgress("24");
        sampleEntries.get(1).setUserRating("10");

        sampleEntries.add(new TestAnime("Oyasumi Punpun", R.drawable.punpun, "Free gallons of depresso espresso", "9.01/10", "Manga"));
        sampleEntries.get(2).setUserStatus("Reading");
        sampleEntries.get(2).setDate("Mar 2007 - Nov 2013");
        sampleEntries.get(2).setUserProgress("42");
        sampleEntries.get(2).setUserRating("1");

        // Default recyclerview settings
        currentType = "Anime";
        currentStatus = "All";
        currentEntries = filterListByStatus("All", currentType, sampleEntries);
        entriesCount.setText(currentEntries.size() + " entries");

        // Library adapter
        RecyclerView recyclerView = findViewById(R.id.library_recycler);  // Initialize the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Set its layout manager
        libraryAdapter = new LibraryAdapter(this, currentEntries, launcher);
        recyclerView.setAdapter(libraryAdapter);                             // Attach the adapter



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
                currentEntries = filterListByStatus(currentStatus, currentType, sampleEntries);
                entriesCount.setText(currentEntries.size() + " entries");
                libraryAdapter.updateEntries(currentEntries);
            }
        });

        //For filtering recyclerview based on watch status and entry type
        // TODO: highlight currently selected status
        allStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentStatus = "All";
                currentEntries = filterListByStatus(currentStatus, currentType, sampleEntries);
                libraryAdapter.updateEntries(currentEntries);
                entriesCount.setText(currentEntries.size() + " entries");
            }
        });

        watchingStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentType.equals("Anime"))
                    currentStatus = "Watching";
                else
                    currentStatus = "Reading";
                currentEntries = filterListByStatus(currentStatus, currentType, sampleEntries);
                libraryAdapter.updateEntries(currentEntries);
                entriesCount.setText(currentEntries.size() + " entries");
            }
        });

        doneStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentStatus = "Completed";
                currentEntries = filterListByStatus(currentStatus, currentType, sampleEntries);
                libraryAdapter.updateEntries(currentEntries);
                entriesCount.setText(currentEntries.size() + " entries");
            }
        });

        planStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentType.equals("Anime"))
                    currentStatus = "Plan to Watch";
                else
                    currentStatus = "Plan to Read";
                currentEntries = filterListByStatus(currentStatus, currentType, sampleEntries);
                libraryAdapter.updateEntries(currentEntries);
                entriesCount.setText(currentEntries.size() + " entries");
            }
        });

        holdStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentStatus = "On Hold";
                currentEntries = filterListByStatus(currentStatus, currentType, sampleEntries);
                libraryAdapter.updateEntries(currentEntries);
                entriesCount.setText(currentEntries.size() + " entries");
            }
        });

    }

    private List<TestAnime> filterListByStatus(String status, String type, List<TestAnime> entries) {
        List<TestAnime> filteredList = new ArrayList<>();
        for (int i = 0; i < entries.size(); i++) {
            TestAnime item = entries.get(i);
            if (item.getType().equals(type)) {
                if (item.getUserStatus().equals(status) || "All".equals(status)) {
                    filteredList.add(item);
                }
            }
        }
        return filteredList;
    }

}