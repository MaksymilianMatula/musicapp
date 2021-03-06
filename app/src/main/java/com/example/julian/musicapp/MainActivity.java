package com.example.julian.musicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.user.myapplication.R;

import com.example.julian.musicapp.topsongs.TopSongsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bTopSongs = findViewById(R.id.bTopSongs);
        bTopSongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TopSongsActivity.class);
                startActivity(intent);
         }
        });
    }
}
