package com.allegra.allegra;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.allegra.allegra.adapter.ResultAdapter;
import com.allegra.allegra.model.Result;
import com.allegra.allegra.room.AppDatabase;
import com.allegra.allegra.room.ResultRoom;

import java.util.List;


public class ResultActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);
        ResultRoom room = AppDatabase.db(this).resultRoom();
        List<Result> listHasil = room.selectAll();
        ResultAdapter adapter = new ResultAdapter(this, listHasil, this);
        RecyclerView recyclerView = findViewById(R.id.result_pencarian);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager man = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(man);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
    }

    @Override
    public void onClick(View view) {
        ResultRoom room = AppDatabase.db(this).resultRoom();
        room.deleteAll();
        startActivity(new Intent(this, HomeActivity.class));
    }
}
