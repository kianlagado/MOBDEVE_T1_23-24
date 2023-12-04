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

public class EditEntryActivity extends AppCompatActivity {
    // TODO: delete entry not yet implemented
    public static String RATING_TAG = "RATING";
    public static String PROGRESS_TAG = "PROGRESS";
    public static String STATUS_TAG = "STATUS";
    public static String POS_TAG = "POS";
    public static String ACTION_TAG = "ACTION";

    TextView entryTitle;
    ImageView entryImage;
    ImageButton exitEdit;
    ImageButton removeEntry;
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
        setContentView(R.layout.activity_edit_entry);

        databaseHelper = new DatabaseHelper(this); // Initialize database helper

        Intent intent = getIntent();
        String title = intent.getStringExtra(LibraryAdapter.TITLE_TAG);
        String image = intent.getStringExtra(LibraryAdapter.IMAGE_TAG);
        String status = intent.getStringExtra(LibraryAdapter.USER_STATUS_TAG);
        String userRating = intent.getStringExtra(LibraryAdapter.USER_RATING_TAG);
        String userProgress = intent.getStringExtra(LibraryAdapter.USER_PROGRESS_TAG);
        String entryType = intent.getStringExtra(LibraryAdapter.TYPE_TAG);
        int position = intent.getIntExtra(LibraryAdapter.POS_TAG, 0);

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
        removeEntry = findViewById(R.id.remove_entry_button);
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

        removeEntry = findViewById(R.id.remove_entry_button);
        removeEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAction = "Remove";
                Intent return_intent = new Intent();
                return_intent.putExtra(ACTION_TAG, selectedAction);
                return_intent.putExtra(POS_TAG, position);
                setResult(Activity.RESULT_OK, return_intent);
                finish();
            }
        });

        confirmBtn = findViewById(R.id.confirm_btn);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAction = "Confirm";

                Intent return_intent = new Intent();
                return_intent.putExtra(RATING_TAG, selectedRating);
                return_intent.putExtra(PROGRESS_TAG, newProgress);
                return_intent.putExtra(STATUS_TAG, selectedStatus);
                return_intent.putExtra(ACTION_TAG, selectedAction);
                return_intent.putExtra(POS_TAG, position);
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