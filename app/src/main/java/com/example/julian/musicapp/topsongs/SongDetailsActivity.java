package com.example.julian.musicapp.topsongs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.julian.musicapp.api.ApiService;
import com.example.julian.musicapp.api.Track;
import com.example.julian.musicapp.api.Tracks;
import com.example.user.myapplication.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongDetailsActivity extends AppCompatActivity {

    public static final String TRACK = "track";
    public static final String ARTIST = "artist";
    public static final String TRACK_ID = "track_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        final String track = intent.getStringExtra(TRACK);
        String artist = intent.getStringExtra(ARTIST);
        int trackId = intent.getIntExtra(TRACK_ID, 0);

        getSupportActionBar().setTitle(track);
        getSupportActionBar().setSubtitle(artist);

        ApiService.getService().getTrack(trackId).enqueue(new Callback<Tracks>() {
            @Override
            public void onResponse(Call<Tracks> call, Response<Tracks> response) {
                Tracks tracks = response.body();
                if (tracks != null && tracks.track.size() > 0) {
                    showData(tracks.track.get(0));
                }


            }

            @Override
            public void onFailure(Call<Tracks> call, Throwable t) {
                Toast.makeText(SongDetailsActivity.this, "Błąd pobierania danych: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void showData(Track track) {
        TextView tvAlbum = findViewById(R.id.tvAlbum);
        TextView tvGenre = findViewById(R.id.tvGenre);
        TextView tvStyle = findViewById(R.id.tvStyle);
        TextView tvDescription = findViewById(R.id.tvDescription);

        tvAlbum.setText(track.strAlbum);
        tvGenre.setText(track.strGenre);
        tvStyle.setText(track.strStyle);
        tvDescription.setText(track.strDescriptionEN);

        if (track.strTrackThumb != null && !track.strTrackThumb.isEmpty()) {
            ImageView ivThumb = findViewById(R.id.ivThumb);
            Glide.with(this).load(track.strTrackThumb).into(ivThumb);
        }
    }
    @Override
    public boolean onSupportNavigateUp() {

        onBackPressed();

        return true;
    }
}
