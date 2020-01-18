package com.allegra.allegra.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.allegra.allegra.model.Result;

@Database(entities = {Result.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public static AppDatabase db(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "allegra")
                .allowMainThreadQueries()
                .build();
    }

    public abstract ResultRoom resultRoom();
    //public abstract DetailRoom detailRoom();

}
