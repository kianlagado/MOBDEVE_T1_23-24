package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity<T> extends AppCompatActivity {
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
    private String currentType;
    private int currentPage;
    private boolean isLoading;
    private ArrayList<T> dataList;
    AnimeAdapter animeAdapter;
    RecyclerView recyclerView;
    private DataService animaExpress;
    Handler handler = new Handler();
    private Runnable loadNextPageRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Default settings
        currentType = "Anime";
        currentPage = 1;
        isLoading = false;
        dataList = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler_view);  // Initialize the RecyclerView

        // -------------Navbar Buttons--------------

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
                searchIntent.putExtra(TYPE_TAG, currentType);
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

        // --------------DATA FETCHING-------------------
        //RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        animaExpress = new DataService(MainActivity.this);
        fetchTop(animaExpress, currentType, currentPage); // load initial data

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
                    dataList.clear();
                    currentPage = 1;
                    currentType = button.getText().toString();
                    fetchTop(animaExpress, currentType, currentPage);

                    // Reset adapter
                    setupAdapter();

                }
            });
        }

        // Initial adapter setup ----------------------------
        setupAdapter();

        //Pagination ----------------------
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPos = layoutManager.findFirstVisibleItemPosition();

                if (!isLoading) {
                    if ((visibleItemCount + firstVisibleItemPos) >= totalItemCount && firstVisibleItemPos >= 0) {
                        handler.removeCallbacks(loadNextPageRunnable);
                        loadNextPageRunnable = new Runnable() {
                            @Override
                            public void run() {
                                currentPage++;
                                loadNextPage(animaExpress, currentType, currentPage);
                            }
                        };
                        handler.postDelayed(loadNextPageRunnable, 500);
                    }
                }
            }
        });






    }

    private void setupAdapter() {
        animeAdapter = new AnimeAdapter(MainActivity.this, dataList, currentType);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this)); // Set its layout manager
        recyclerView.setAdapter(animeAdapter); // Attach the adapter
    }

    private void loadNextPage(DataService animaExpress, String currentType, int currentPage) {
        if (isLoading)
            return;
        isLoading = true;
        fetchTop(animaExpress, currentType, currentPage);
        isLoading = false;
    }

    private void fetchTop(DataService animaExpress, String currentType, int currentPage) {
        animaExpress.displayTop(currentType, currentPage, new DataService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(MainActivity.this, "Failed to obtain Top " + currentType, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(ArrayList response) {
                int startIndex = dataList.size();
                dataList.addAll(response);

                animeAdapter.updateData(dataList, startIndex);

//                Toast.makeText(MainActivity.this, String.valueOf(currentPage), Toast.LENGTH_SHORT).show();

                animeAdapter.setOnItemClickListener(new AnimeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent detailIntent = new Intent(MainActivity.this, DetailActivity.class);
                        // If needed, pass extra data to the DetailActivity using putExtra
                        T data = dataList.get(position);
//                        Toast.makeText(MainActivity.this, data.toString(), Toast.LENGTH_SHORT).show();

                        if (currentType.equals("Anime")) {
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
                        else if (currentType.equals("Manga")) {
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
                        detailIntent.putExtra(TYPE_TAG, currentType);
                        startActivity(detailIntent);
                    }
                });




            }
        });
    }


}

