package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddToLibraryActivity extends AppCompatActivity {

    private ImageButton btnClose;
    private androidx.appcompat.widget.AppCompatButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_library);

        // Initialize the close button and set its click listener
        btnClose = findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  // Close this activity and return to the previous one
            }
        });

        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display a toast message for now
                Toast.makeText(AddToLibraryActivity.this, "Added to Library", Toast.LENGTH_SHORT).show();
            }
        });
    }
}