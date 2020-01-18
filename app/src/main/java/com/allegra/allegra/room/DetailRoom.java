package com.allegra.allegra.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.allegra.allegra.model.Detail;

import java.util.List;

@Dao
public interface DetailRoom {

    @Query("SELECT * FROM detail WHERE idDetail = :idDetail")
    Detail select(int idDetail);

    @Query("SELECT * FROM detail")
    List<Detail> selectAll();

    @Insert
    void insert(Detail detail);

    @Query("Delete from detail")
    void deleteAll();
}
