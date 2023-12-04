package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    TextView titleText;
    ImageView itemImage;
    TextView ratingText;
    TextView descText;
    TextView genreText;
    TextView epsText;
    TextView dateText;
    TextView studiosText;
    TextView statusText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent i = getIntent();
        String title = i.getStringExtra(MainActivity.TITLE_TAG);
        String image = i.getStringExtra(MainActivity.IMAGE_TAG);
        String rating = i.getStringExtra(MainActivity.RATING_TAG);
        String desc = i.getStringExtra(MainActivity.DESC_TAG);
        String genres = i.getStringExtra(MainActivity.GENRES_TAG);
        int episodes = i.getIntExtra(MainActivity.EPISODES_TAG, 0);
        String date = i.getStringExtra(MainActivity.DATE_TAG);
        String studios = i.getStringExtra(MainActivity.STUDIOS_TAG);
        String status = i.getStringExtra(MainActivity.STATUS_TAG);


        titleText = findViewById(R.id.entry_title);
        itemImage = findViewById(R.id.entry_image);
        ratingText = findViewById(R.id.tv_detail_rating);
        descText = findViewById(R.id.tv_synopsis_content);
        genreText = findViewById(R.id.tv_genres);
        epsText = findViewById(R.id.tv_episodes);
        dateText = findViewById(R.id.tv_airing_date);
        studiosText = findViewById(R.id.tv_studio);
        statusText = findViewById(R.id.tv_status);


        titleText.setText(title);
        Picasso.get().load(image).into(itemImage); // Use Picasso to load the image from the URL
        ratingText.setText("Rating: " + rating);
        descText.setText(desc);
        genreText.setText(genres);
        epsText.setText(String.valueOf(episodes));
        dateText.setText(date);
        studiosText.setText(studios);
        statusText.setText(status);



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
