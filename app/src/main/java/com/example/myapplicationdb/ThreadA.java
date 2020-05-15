package com.example.myapplicationdb;

import android.content.Context;

import com.example.myapplicationdb.database.*;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ThreadA extends Thread {
    Context context;
    Map<Integer,Map<Integer,Map<String,Integer>>> map = new TreeMap<>();
    CallBack callBack;

    ThreadA(Context context,Map<Integer,Map<Integer,Map<String,Integer>>> map){
        this.context = context;
        this.map = map;
    }

    @Override
    public void run() {
        AppDatabase appDatabase = AppDatabaseSingleton.getInstance(context);
        for(int date:map.keySet()){
            Map<Integer, Map<String,Integer>> dateMap = map.get(date);
            for(int no:dateMap.keySet()){
                Map<String,Integer> cateMap = dateMap.get(no);
                //  for(String kate:map.get(date).get(no).keySet()){
                appDatabase.mainDataDao().insert(new MainData(no,date,11,cateMap.get("BB"),cateMap.get("RB"),cateMap.get("TOTAL"),cateMap.get("LAST"),cateMap.get("DIFF")));
                // }
            }
        }
    }

    public void setCallBack(CallBack callBack){
        this.callBack = callBack;
    }

   static interface CallBack{
        void callBack(List<MainData> mainDataList);
    }
}
