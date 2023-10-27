package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class LibraryActivity extends AppCompatActivity {

    // TODO: change toggle button to a better looking one
    ToggleButton toggleList;
    TextView planStatus;
    ImageButton profileIcon;
    ImageButton filterIcon;
    ImageButton searchIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        toggleList = (ToggleButton) findViewById(R.id.toggle_list);
        planStatus = (TextView) findViewById(R.id.planned_status);
        profileIcon = (ImageButton) findViewById(R.id.library_profile_icon);
        filterIcon = (ImageButton) findViewById(R.id.library_filter_icon);
        searchIcon = (ImageButton) findViewById(R.id.library_search_icon);


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