package com.example.myapplicationdb;

import android.content.Context;

import com.example.myapplicationdb.database.*;
import com.example.myapplicationdb.database.AppDatabaseSingleton;

import java.util.List;

public class PullData extends Thread {
    String modelname;
    String modelno;
    Context context;
    PullData(Context context, String modelname, String modelno){
        this.context = context;
        this.modelname = modelname;
        this.modelno = modelno;
    }
    @Override
    public void run() {
        AppDatabase appDatabase = AppDatabaseSingleton.getInstance(context);
        //List<MainData> mainDataList = appDatabase.mainDataDao().getAll();
        List<MainData> mainDataList = appDatabase.mainDataDao().queryModelno(Integer.parseInt(modelno));

    }
}
