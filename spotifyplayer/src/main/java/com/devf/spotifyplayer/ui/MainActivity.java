package com.devf.spotifyplayer.ui;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.devf.spotifyplayer.R;
import com.devf.spotifyplayer.data.ServiceGenerator;
import com.devf.spotifyplayer.data.SpotifyApi;
import com.devf.spotifyplayer.models.Item;
import com.devf.spotifyplayer.models.TrackObject;
import com.devf.spotifyplayer.models.Tracks;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @BindView(R.id.input_track)
    EditText inputTrack;

    @BindView(R.id.rv_tracks)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mediaPlayer = new MediaPlayer();
    }

    @OnClick(R.id.btn_search)
    public void onClick() {
        String query = inputTrack.getText().toString();
        searchTracks(query);
    }

    public void  settingRecyclerView(List<Item> items){
        TracksAdapter tracksAdapter = new TracksAdapter(items, mediaPlayer);

        // Layout manager que define la manera en la que se organizan
        // mis elementos dentro del recyclerview
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(MainActivity.this,
                        LinearLayoutManager.VERTICAL, false);

        // Separador entre los elementos del recyclerview
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());

        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(tracksAdapter);
    }


    public void searchTracks(String query) {
        SpotifyApi spotifyApi = ServiceGenerator.createService();

        spotifyApi.searchTracks(query, "track").enqueue(new Callback<TrackObject>() {

            @Override
            public void onResponse(Call<TrackObject> call, Response<TrackObject> response) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        TrackObject trackObject = response.body();
                        Tracks tracks = trackObject.getTracks();
                        if (tracks.getItems().size() > 0) {
                            Item item = tracks.getItems().get(0);
                            //play(item.getPreviewUrl());
                            Log.e("myLog", trackObject.toString());
                            // Llamamos al método para ejecutarlo
                            settingRecyclerView(tracks.getItems());
                        } else {
                            Toast.makeText(MainActivity.this, "NO ENCONTRE CANCIONES", Toast.LENGTH_SHORT).show();
                        }


                    }
                }
            }

            @Override
            public void onFailure(Call<TrackObject> call, Throwable t) {

            }
        });
    }
}