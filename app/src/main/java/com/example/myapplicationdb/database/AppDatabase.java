package com.example.myapplicationdb.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MainData.class,ModelTable.class},version=1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MainDataDao mainDataDao();
    public abstract ModelTableDao modelTableDao();
}

