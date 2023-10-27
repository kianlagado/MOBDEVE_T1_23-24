package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FilterSortActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_sort);

        // Populating the Spinner
        Spinner genreSpinner = findViewById(R.id.genre_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.genres_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genreSpinner.setAdapter(adapter);

        // Close Button functionality
        ImageButton closeButton = findViewById(R.id.close_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        androidx.appcompat.widget.AppCompatButton btn_apply = findViewById(R.id.btn_apply);
        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display a toast message for now
                Toast.makeText(FilterSortActivity.this, "Filters Applied", Toast.LENGTH_SHORT).show();
            }
        });

        androidx.appcompat.widget.AppCompatButton btn_reset = findViewById(R.id.btn_reset);
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display a toast message for now
                Toast.makeText(FilterSortActivity.this, "Filters Reset", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
