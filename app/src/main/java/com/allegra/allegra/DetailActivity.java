package com.allegra.allegra;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.allegra.allegra.adapter.ResultAdapter;
import com.allegra.allegra.api.ClientService;
import com.allegra.allegra.api.RetrofitClient;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetailActivity extends AppCompatActivity {

    TextView tvArtist;
    TextView tvTrack;
    TextView tvLyrics;
    TextView tvWarning;
    ImageView ivCover;
    ProgressBar progress;
    TextView apiKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvArtist = (TextView) findViewById(R.id.detail_artist);
        tvTrack = (TextView) findViewById(R.id.detail_track);
        tvLyrics = (TextView) findViewById(R.id.detail_lyrics);
        tvWarning = (TextView) findViewById(R.id.warning);
        progress = (ProgressBar) findViewById(R.id.progressBar);
        apiKey = (TextView) findViewById(R.id.detail_api);
        ivCover = findViewById(R.id.detail_cover);

        progress.setVisibility(View.VISIBLE);
        apiKey.setVisibility(View.INVISIBLE);

        Intent i = getIntent();

        if (i == null)
            return;

        int idArtist = i.getIntExtra(ResultAdapter.KEY_ARTIST, -1);
        int idAlbum = i.getIntExtra(ResultAdapter.KEY_ALBUM, -1);
        int idTrack = i.getIntExtra(ResultAdapter.KEY_TRACK, -1);
        String artist = i.getStringExtra(ResultAdapter.KEY_NAMA_ARTIST);
        String track = i.getStringExtra(ResultAdapter.KEY_NAMA_TRACK);
        String cover = i.getStringExtra(ResultAdapter.KEY_COVER);

        //Toast.makeText(this,"idArtist : " + idArtist + "idArtist : " +
          //      idAlbum + "idAlbum : " + idTrack + "track : " + track, Toast.LENGTH_SHORT).show();

        Retrofit retrofit2 = RetrofitClient.connect();
        ClientService service2 = retrofit2.create(ClientService.class);
        final Call<ResponseBody> request2 = service2.lyrics(
                idArtist,
                idAlbum,
                idTrack,
                apiKey.getText().toString()
        ); 
        request2.enqueue(new Callback<ResponseBody>()
        {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progress.setVisibility(View.GONE);
                try{
                    String json2 = response.body().string();
                    Log.e("Response2", json2);
                    JSONObject obj2 = new JSONObject(json2);
                    boolean status = obj2.getBoolean("success");
                    JSONObject result2 = obj2.getJSONObject("result");

                    if (status == true) {
                        tvWarning.setVisibility(View.INVISIBLE);
                        //Toast.makeText(DetailActivity.this,"API 2 Berhasil", Toast.LENGTH_SHORT).show();
                        //DetailRoom detailRoom = AppDatabase.db(DetailActivity.this).detailRoom();
                        //detailRoom.deleteAll();

                        String lyrics = result2.getString("lyrics");
                        tvLyrics.setText(lyrics);

                        //Toast.makeText(DetailActivity.this,"lyrics : " + lyrics, Toast.LENGTH_LONG).show();

                        //Detail detailModel = new Detail();
                        //detailModel.setLyrics(lyrics);
                        //detailRoom.insert(detailModel);
                    } else if (status == false) {
                        //tvLyrics.setText("Sorry, lyrics doesn't exist.");
                        tvWarning.setVisibility(View.VISIBLE);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "API Gagal", Toast.LENGTH_SHORT).show();
            }
        });

        Picasso.with(this)
                .load(cover)
                .into(ivCover);
        tvArtist.setText((artist));
        tvTrack.setText((track));


        //----------------

    }

}
