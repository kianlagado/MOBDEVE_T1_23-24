package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageButton backButton = findViewById(R.id.btn_exit_detail);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageButton btnAddToLibrary = findViewById(R.id.btn_add_to_library);
        btnAddToLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to launch the AddToLibraryActivity
                Intent intent = new Intent(DetailActivity.this, AddToLibraryActivity.class);

                // Start the activity
                startActivity(intent);
            }
        });
        /*
        Intent intent = getIntent();
        if (intent != null) {
            Anime receivedAnime = intent.getParcelableExtra("SELECTED_ANIME");
            // Now you can use 'receivedAnime' to populate your views in DetailActivity.
        }*/
    }
}
