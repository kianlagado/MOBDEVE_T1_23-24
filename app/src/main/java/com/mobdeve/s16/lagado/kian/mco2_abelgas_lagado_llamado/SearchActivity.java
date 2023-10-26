package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private ListView searchListView;
    private SearchResultAdapter searchResultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Initialize the ListView and Adapter
        searchListView = findViewById(R.id.search_list_view);
        List<Search> searchResults = getSearchResults();
        searchResultAdapter = new SearchResultAdapter(this, searchResults);
        searchListView.setAdapter(searchResultAdapter);

        // Item click listener
        searchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Search selectedSearchItem = (Search) parent.getItemAtPosition(position);
                // Handle the item click here
            }
        });

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
        results.add(new Search(R.drawable.thumbnail, "Shingeki no Kyojin", "10/10"));
        results.add(new Search(R.drawable.thumbnail, "Attack on Titan", "10/10"));
        // ... add more data as needed
        return results;
    }
}
