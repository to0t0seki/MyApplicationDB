package com.example.myapplicationdb.database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public abstract class MainDataDao {
    @Query("select N from MainData order by N desc limit 1")
    public abstract   int getMaxId();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(MainData mainData);

    @Query("delete from MainData where N = :no")
    abstract void delete(int no);

    @Query("select * from MainData")
    public abstract List<MainData> getAll();

    @Query("select * from MainData where N = :no")
    public abstract List<MainData> queryno(int no);


    @Query("select * from MainData where modelno = :modelno")
    public abstract List<MainData> queryModelno(int modelno);

}
