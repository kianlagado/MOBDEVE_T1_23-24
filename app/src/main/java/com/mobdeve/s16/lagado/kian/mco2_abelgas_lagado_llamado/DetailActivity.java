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
    TextView epsHeader;
    TextView epsText;
    TextView dateHeader;
    TextView dateText;
    TextView studiosHeader;
    TextView studiosText;
    TextView statusText;

    int episodes, chapters;
    String studios, authors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent i = getIntent();
        String type = i.getStringExtra(MainActivity.TYPE_TAG);
        String title = i.getStringExtra(MainActivity.TITLE_TAG);
        String image = i.getStringExtra(MainActivity.IMAGE_TAG);
        String rating = i.getStringExtra(MainActivity.RATING_TAG);
        String desc = i.getStringExtra(MainActivity.DESC_TAG);
        String genres = i.getStringExtra(MainActivity.GENRES_TAG);
        String date = i.getStringExtra(MainActivity.DATE_TAG);
        String status = i.getStringExtra(MainActivity.STATUS_TAG);

        if (type.equals("Anime")) {
            episodes = i.getIntExtra(MainActivity.EPISODES_TAG, 0);
            studios = i.getStringExtra(MainActivity.STUDIOS_TAG);
        }
        else if (type.equals("Manga")) {
            chapters = i.getIntExtra(MainActivity.CHAPTERS_TAG, 0);
            authors = i.getStringExtra(MainActivity.AUTHORS_TAG);
        }


        titleText = findViewById(R.id.entry_title);
        itemImage = findViewById(R.id.entry_image);
        ratingText = findViewById(R.id.tv_detail_rating);
        descText = findViewById(R.id.tv_synopsis_content);
        genreText = findViewById(R.id.tv_genres);
        epsHeader = findViewById(R.id.tv_episodes_header);
        epsText = findViewById(R.id.tv_episodes);
        dateHeader = findViewById(R.id.tv_airing_header);
        dateText = findViewById(R.id.tv_airing_date);
        studiosHeader = findViewById(R.id.tv_studio_header);
        studiosText = findViewById(R.id.tv_studio);
        statusText = findViewById(R.id.tv_status);



        titleText.setText(title);
        Picasso.get().load(image).into(itemImage); // Use Picasso to load the image from the URL
        ratingText.setText("Rating: " + rating);
        descText.setText(desc);
        genreText.setText(genres);

        if (type.equals("Anime")) {
            epsHeader.setText("Episodes");
            studiosHeader.setText("Studios");
            dateHeader.setText("Aired On");
            epsText.setText(String.valueOf(episodes));
            studiosText.setText(studios);
        }
        else if (type.equals("Manga")) {
            epsHeader.setText("Chapters");
            studiosHeader.setText("Authors");
            dateHeader.setText("Published On");
            epsText.setText(String.valueOf(chapters));
            studiosText.setText(authors);
        }
        dateText.setText(date);
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

    }
}
