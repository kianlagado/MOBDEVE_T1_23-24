package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class EditEntryActivity extends AppCompatActivity {

    TextView entryTitle;
    ImageView entryImage;
    ImageButton exitEdit;
    ImageButton removeEntry;
    Button watchingBtn;
    Button planBtn;
    Button cancelBtn;
    EditText editProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entry);

        Intent i = getIntent();
        String title = i.getStringExtra(LibraryAdapter.TITLE_TAG);
        Integer image = i.getIntExtra(LibraryAdapter.IMAGE_TAG, 0);
        String status = i.getStringExtra(LibraryAdapter.USER_STATUS_TAG);
        String userRating = i.getStringExtra(LibraryAdapter.USER_RATING_TAG);
        String userProgress = i.getStringExtra(LibraryAdapter.USER_PROGRESS_TAG);
        String entryType = i.getStringExtra(LibraryAdapter.TYPE_TAG);

        entryTitle = findViewById(R.id.entry_title);
        entryTitle.setText(title);

        entryImage = findViewById(R.id.entry_image);
        entryImage.setImageResource(image);

        watchingBtn = findViewById(R.id.watching_btn);
        if (entryType.equals("Manga")) watchingBtn.setText("Reading");

        planBtn = findViewById(R.id.plan_btn);
        if (entryType.equals("Manga")) planBtn.setText("Plan to Read");

        editProgress = findViewById(R.id.editProgress);
        editProgress.setHint(userProgress);

        exitEdit = findViewById(R.id.exit_edit_button);
        removeEntry = findViewById(R.id.remove_entry_button);
        cancelBtn = findViewById(R.id.cancel_btn);

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