package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView searchRecyclerView;
    private SearchResultAdapter searchResultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Initialize the RecyclerView and Adapter
        searchRecyclerView = findViewById(R.id.recycler_search);
        List<Search> searchResults = getSearchResults();
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
    }

    private List<Search> getSearchResults() {
        List<Search> results = new ArrayList<>();
        results.add(new Search(R.drawable.thumbnail, "Stein's Gate", "10/10"));
        results.add(new Search(R.drawable.thumbnail, "Attack on Titan", "10/10"));
        // ... add more data as needed
        return results;
    }
}
