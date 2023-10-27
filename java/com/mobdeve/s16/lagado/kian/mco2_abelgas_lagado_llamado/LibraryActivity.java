package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

public class LibraryActivity extends AppCompatActivity {

    // TODO: change toggle button to a better looking one
    ToggleButton toggleList;
    TextView planStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        toggleList = (ToggleButton) findViewById(R.id.toggle_list);
        planStatus = (TextView) findViewById(R.id.planned_status);

        toggleList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!toggleList.isChecked()) {
                    planStatus.setText("Plan to Watch");
                }
                else {
                    planStatus.setText("Plan to Read");
                }
            }
        });

    }
}