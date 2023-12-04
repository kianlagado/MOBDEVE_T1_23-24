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
    LinearLayout ratingBtnLayout;
    List<LinearLayout> statusLayouts;
    List<Button> statusButtons;
    List<Button> ratingButtons;
    private String selectedRating;
    private String selectedStatus;
    private String newProgress;
    private String selectedAction;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_library);

        databaseHelper = new DatabaseHelper(this); // Initialize database helper

        Intent intent = getIntent();
        int id = intent.getIntExtra(DetailActivity.ID_TAG, 0);
        String title = intent.getStringExtra(DetailActivity.TITLE_TAG);
        String image = intent.getStringExtra(DetailActivity.IMAGE_TAG);
        String desc = intent.getStringExtra(DetailActivity.DESC_TAG);
        String genres = intent.getStringExtra(DetailActivity.GENRES_TAG);
        String date = intent.getStringExtra(DetailActivity.DATE_TAG);
        String status = "";
        String userRating = "";
        String userProgress = "";
        String entryType = intent.getStringExtra(DetailActivity.TYPE_TAG);

        // Setting default values for new entry
        selectedRating = (userRating != null) ? userRating : "Unrated";
        selectedStatus = (status != null) ? status : "Plan to Watch"; // Default status
        newProgress = (userProgress != null) ? userProgress : "0"; // Default progress


        entryTitle = findViewById(R.id.entry_title);
        entryTitle.setText(title);

        entryImage = findViewById(R.id.entry_image);
        Picasso.get().load(image).into(entryImage);

        watchingBtn = findViewById(R.id.watching_btn);
        if (entryType.equals("Manga")) watchingBtn.setText("Reading");

        planBtn = findViewById(R.id.plan_btn);
        if (entryType.equals("Manga")) planBtn.setText("Plan to Read");

        exitEdit = findViewById(R.id.exit_edit_button);
        cancelBtn = findViewById(R.id.cancel_btn);

        // Setting current progress as hint and obtaining new progress input
        editProgress = findViewById(R.id.editProgress);
        editProgress.setHint(userProgress);
        newProgress = editProgress.getText().toString();

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
                if (entryType.equals("Anime")) {
                    TestAnime anime = new TestAnime();
                    // Set properties for anime
                    anime.setMal_id(id);
                    anime.setImageUrl(image/);
                    anime.setTitle(title);
                    anime.setEpisodes();
                    anime.setStatus(selectedStatus);
                    anime.setScore(selectedRating);
                    anime.setSynopsis(/* Synopsis */);
                    anime.setDate(/* Date */);
                    anime.setStudios(/* Studios */);
                    anime.setGenres(/* Genres */);

                    // Save to database
                    databaseHelper.addAnime(anime);
                } else if (entryType.equals("Manga")) {
                    Manga manga = new Manga();
                    // Set properties for manga
                    manga.setMal_id(/* ID from intent or generate new */);
                    manga.setImageUrl(/* Image URL */);
                    manga.setTitle(title);
                    manga.setChapters(/* Number of chapters */);
                    manga.setStatus(selectedStatus);
                    manga.setScore(selectedRating);
                    manga.setSynopsis(/* Synopsis */);
                    manga.setDate(/* Date */);
                    manga.setAuthors(/* Authors */);
                    manga.setGenres(/* Genres */);

                    // Save to database
                    databaseHelper.addManga(manga);
                }

                Intent return_intent = new Intent();
                return_intent.putExtra(RATING_TAG, selectedRating);
                return_intent.putExtra(PROGRESS_TAG, newProgress);
                return_intent.putExtra(STATUS_TAG, selectedStatus);
                return_intent.putExtra(ACTION_TAG, selectedAction);
                setResult(Activity.RESULT_OK, return_intent);
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