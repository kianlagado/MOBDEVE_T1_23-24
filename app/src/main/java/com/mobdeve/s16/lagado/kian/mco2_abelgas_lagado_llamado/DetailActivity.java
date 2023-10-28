package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView titleText;
    TextView ratingText;
    TextView descText;
    TextView dateText;
    ImageView itemImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent i = getIntent();
        String title = i.getStringExtra(MainActivity.TITLE_TAG);
        String rating = i.getStringExtra(MainActivity.RATING_TAG);
        String desc = i.getStringExtra(MainActivity.DESC_TAG);
        String date = i.getStringExtra(MainActivity.YEAR_TAG);
        Integer image = i.getIntExtra(MainActivity.IMAGE_TAG, 0);

        titleText = findViewById(R.id.entry_title);
        ratingText = findViewById(R.id.tv_detail_rating);
        descText = findViewById(R.id.tv_synopsis_content);
        dateText = findViewById(R.id.tv_airing_date);
        itemImage = findViewById(R.id.entry_image);

        titleText.setText(title);
        ratingText.setText("Rating: " + rating);
        descText.setText(desc);
        dateText.setText(date);
        itemImage.setImageResource(image);

        ImageButton backButton = findViewById(R.id.exit_edit_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageButton btnAddToLibrary = findViewById(R.id.remove_entry_button);

        // TODO: make button invisible if anime/manga is already in library
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
