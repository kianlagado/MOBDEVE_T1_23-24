package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AddToLibraryActivity extends AppCompatActivity {
    public static String RATING_TAG = "RATING";
    public static String PROGRESS_TAG = "PROGRESS";
    public static String STATUS_TAG = "STATUS";
    public static String POS_TAG = "POS";
    public static String ACTION_TAG = "ACTION";

    TextView entryTitle;
    ImageView entryImage;
    ImageButton exitEdit;
    Button watchingBtn;
    Button planBtn;
    Button confirmBtn;
    Button cancelBtn;
    EditText editProgress;
    TextView progressTotal;
    LinearLayout ratingBtnLayout;
    List<LinearLayout> statusLayouts;
    List<Button> statusButtons;
    List<Button> ratingButtons;
    private String selectedRating;
    private String selectedStatus;
    private String newProgress;
    private String selectedAction;
    int episodes, chapters;
    String studios, authors;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_library);

        databaseHelper = new DatabaseHelper(AddToLibraryActivity.this); // Initialize database helper

        Intent intent = getIntent();
        String type = intent.getStringExtra(DetailActivity.TYPE_TAG);
        int id = intent.getIntExtra(DetailActivity.ID_TAG, 0);
        String imageUrl = intent.getStringExtra(DetailActivity.IMAGE_TAG);
        String title = intent.getStringExtra(DetailActivity.TITLE_TAG);
        String score = intent.getStringExtra(DetailActivity.RATING_TAG);
        String synopsis = intent.getStringExtra(DetailActivity.DESC_TAG);
        String genres = intent.getStringExtra(DetailActivity.GENRES_TAG);
        String date = intent.getStringExtra(DetailActivity.DATE_TAG);
        String status = intent.getStringExtra(DetailActivity.STATUS_TAG);

        if (type.equals("Anime")) {
            episodes = intent.getIntExtra(MainActivity.EPISODES_TAG, 0);
            studios = intent.getStringExtra(MainActivity.STUDIOS_TAG);
        }
        else if (type.equals("Manga")) {
            chapters = intent.getIntExtra(MainActivity.CHAPTERS_TAG, 0);
            authors = intent.getStringExtra(MainActivity.AUTHORS_TAG);
        }


        // Setting default values for new entry
        selectedRating = "-"; // Default rating / unrated
        selectedStatus = "Plan to Watch"; // Default status
        newProgress = "0";  // Default progress


        entryTitle = findViewById(R.id.entry_title);
        entryTitle.setText(title);

        entryImage = findViewById(R.id.entry_image);
        Picasso.get().load(imageUrl).into(entryImage);

        watchingBtn = findViewById(R.id.watching_btn);
        if (type.equals("Manga")) watchingBtn.setText("Reading");

        planBtn = findViewById(R.id.plan_btn);
        if (type.equals("Manga")) planBtn.setText("Plan to Read");

        exitEdit = findViewById(R.id.exit_edit_button);
        cancelBtn = findViewById(R.id.cancel_btn);

        // Setting current progress as hint and obtaining new progress input
        editProgress = findViewById(R.id.editProgress);
        editProgress.setHint("0");
        newProgress = editProgress.getText().toString();

        progressTotal = findViewById(R.id.progressTotal);
        if (type.equals("Anime")) progressTotal.setText("/ " + String.valueOf(episodes) + " episodes");
        if (type.equals("Manga")) progressTotal.setText("/ " + String.valueOf(chapters) + " chapters");


        // For highlighting and getting the currently selected rating value
        ratingBtnLayout = findViewById(R.id.rating_btns_layout);
        ratingButtons = new ArrayList<>();
        for (int i = 0; i < ratingBtnLayout.getChildCount(); i++) {
            Button button = (Button) ratingBtnLayout.getChildAt(i);
            ratingButtons.add(button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (Button item : ratingButtons) {
                        item.setBackgroundColor(getResources().getColor(R.color.edit_entry_bg));
                        item.setActivated(false);
                    }
                    button.setActivated(true);
                    button.setBackgroundColor(getResources().getColor(R.color.edit_status_btn));
                    selectedRating = button.getText().toString();
                }
            });

        }

        // For highlighting and getting currently selected status
        statusLayouts = new ArrayList<>();
        statusLayouts.add(findViewById(R.id.status_btns_layout));
        statusLayouts.add(findViewById(R.id.status_btns_layout2));
        statusButtons = new ArrayList<>();
        for (int i = 0; i < statusLayouts.size(); i++) {
            for (int j = 0; j < statusLayouts.get(i).getChildCount(); j++) {
                Button button = (Button) statusLayouts.get(i).getChildAt(j);
                statusButtons.add(button);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (Button item : statusButtons) {
                            item.setBackgroundColor(getResources().getColor(R.color.edit_status_btn));
                            item.setActivated(false);
                        }
                        button.setActivated(true);
                        button.setBackgroundColor(getResources().getColor(R.color.selected_status_btn));
                        selectedStatus = button.getText().toString();
                    }
                });
            }
        }

        confirmBtn = findViewById(R.id.confirm_btn);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAction = "Confirm";

                newProgress = editProgress.getText().toString(); // Capture the progress input

                // Assuming you have a way to determine whether it's Anime or Manga
                if (type.equals("Anime")) {
                    TestAnime anime = new TestAnime(
                        id,
                        imageUrl,
                        title,
                        episodes,
                        status,
                        score,
                        synopsis,
                        date,
                        studios,
                        genres
                    );


                    anime.setUserRating(selectedRating);

                    if (newProgress.equals(""))
                        anime.setUserProgress("0");
                    else
                        anime.setUserProgress(newProgress);

                    anime.setUserStatus(selectedStatus);

                    // Save to database
                    if (databaseHelper.getAnime(id) == null)
                        databaseHelper.addAnime(anime);
                    else
                        Toast.makeText(AddToLibraryActivity.this, "Anime is already in library", Toast.LENGTH_SHORT).show();
                } else if (type.equals("Manga")) {
                    Manga manga = new Manga(
                        id,
                        imageUrl,
                        title,
                        chapters,
                        status,
                        score,
                        synopsis,
                        date,
                        authors,
                        genres
                    );

                    manga.setUserRating(selectedRating);
                    if (newProgress.equals(""))
                        manga.setUserProgress("0");
                    else
                        manga.setUserProgress(newProgress);

                    manga.setUserStatus(selectedStatus);

                    // Save to database
                    if (databaseHelper.getManga(id) == null)
                        databaseHelper.addManga(manga);
                    else
                        Toast.makeText(AddToLibraryActivity.this, "Manga is already in library", Toast.LENGTH_SHORT).show();
                }

                Intent libIntent = new Intent(AddToLibraryActivity.this, LibraryActivity.class);
                startActivity(libIntent);
                finish();
            }
        });


        exitEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}