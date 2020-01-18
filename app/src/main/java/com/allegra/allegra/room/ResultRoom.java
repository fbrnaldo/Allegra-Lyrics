package com.allegra.allegra.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.allegra.allegra.model.Result;

import java.util.List;

@Dao
public interface ResultRoom {

    @Query("SELECT * FROM result WHERE idResult = :id")
    Result select(int id);

    @Query("SELECT * FROM result")
    List<Result> selectAll();

    @Insert
    void insert(Result result);

    @Query("Delete from result")
    void deleteAll();
}
