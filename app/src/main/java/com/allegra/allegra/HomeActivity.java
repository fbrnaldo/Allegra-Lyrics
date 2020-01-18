package com.allegra.allegra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.allegra.allegra.api.ClientService;
import com.allegra.allegra.api.RetrofitClient;
import com.allegra.allegra.model.Result;
import com.allegra.allegra.room.AppDatabase;
import com.allegra.allegra.room.ResultRoom;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSearch;
    EditText edt_search;
    TextView apiKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        edt_search = findViewById(R.id.edt_search);
        btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);
        apiKey = findViewById(R.id.api);
        apiKey.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View view) {
        Retrofit retrofit = RetrofitClient.connect();
        ClientService service = retrofit.create(ClientService.class);
        final Call<ResponseBody> request = service.search(
                edt_search.getText().toString(),
                apiKey.getText().toString()
        );
        request.enqueue(new Callback<ResponseBody>()
        {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    String json = response.body().string();
                    Log.e("Response", json);
                    JSONObject obj = new JSONObject(json);
                    boolean status = obj.getBoolean("success");
                    JSONArray result = obj.getJSONArray("result");

                    if (status == true) {
                        Toast.makeText(HomeActivity.this,"Searching...", Toast.LENGTH_SHORT).show();
                        ResultRoom room = AppDatabase.db(HomeActivity.this).resultRoom();
                        room.deleteAll();
                        for (int i = 0; i < result.length(); i++) {
                            JSONObject hasil = result.getJSONObject(i);
                            int idArtist = hasil.getInt("id_artist");
                            int idAlbum = hasil.getInt("id_album");
                            int idTrack = hasil.getInt("id_track");
                            String artist = hasil.getString("artist");
                            String album = hasil.getString("album");
                            String track = hasil.getString("track");
                            String cover = hasil.getString("cover");

                            Result resultModel = new Result();
                            resultModel.setIdArtist(idArtist);
                            resultModel.setArtist(artist);
                            resultModel.setIdAlbum(idAlbum);
                            resultModel.setAlbum(album);
                            resultModel.setIdTrack(idTrack);
                            resultModel.setTrack(track);
                            resultModel.setCover(cover);
                            room.insert(resultModel);
                        }
                        startActivity(new Intent(HomeActivity.this, ResultActivity.class));
                    } else {
                        onFailure(null, null);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "API Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
