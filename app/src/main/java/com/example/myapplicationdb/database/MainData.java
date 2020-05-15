package com.example.myapplicationdb.database;

import androidx.room.Entity;

@Entity(primaryKeys = {"N", "date"})
public class MainData {
    public int N;
    public int modelno;
    public int BB;
    public int RB;
    public int total;
    public int last;
    public int diff;
    public int date;




    public MainData(int N,int date,int modelno,int BB,int RB,int total,int last,int diff){
        this.N = N;
        this.date = date;
        this.modelno = modelno;
        this.BB  =BB;
        this.RB = RB;
        this.total = total;
        this.last = last;
        this.diff = diff;
    }
}
