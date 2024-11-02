package com.natalia.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.natalia.myapplication.databinding.ActivityTabsBinding;



public class SobreActivity extends AppCompatActivity {

    private ActivityTabsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        FloatingActionButton fabInicio = findViewById(R.id.fab2);
        fabInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SobreActivity.this, TabsActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
