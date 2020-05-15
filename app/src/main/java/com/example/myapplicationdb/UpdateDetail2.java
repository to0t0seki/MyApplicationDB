package com.example.myapplicationdb;

import android.content.Context;
import android.os.AsyncTask;

import com.example.myapplicationdb.database.*;
import com.example.myapplicationdb.database.AppDatabaseSingleton;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class UpdateDetail2 extends AsyncTask<String,Void, Map<Integer,Map<Integer,Map<String,Integer>>>> {
    Map<String,String> contentmap = new HashMap<>();
    Callback callback;
    private int time[]=new int[6];
    Context context;


    UpdateDetail2(Context context){
        this.context = context;
        contentmap.put("81/1","DIFF");
        contentmap.put("1/0","BB");
        contentmap.put("2/0","RB");
        contentmap.put("6/0","TOTAL");
        contentmap.put("8/0","LAST");
        SimpleDateFormat sn =new SimpleDateFormat("yyyyMMdd");
        Calendar cal=Calendar.getInstance();
        this.time[0] =Integer.parseInt(sn.format(cal.getTime()));
        cal.add(Calendar.DATE, -1);
        this.time[1] =Integer.parseInt(sn.format(cal.getTime()));
        cal.add(Calendar.DATE, -1);
        this.time[2] =Integer.parseInt(sn.format(cal.getTime()));
        cal.add(Calendar.DATE, -1);
        this.time[3] =Integer.parseInt(sn.format(cal.getTime()));
        cal.add(Calendar.DATE, -1);
        this.time[4] =Integer.parseInt(sn.format(cal.getTime()));
        cal.add(Calendar.DATE, -1);
        this.time[5] =Integer.parseInt(sn.format(cal.getTime()));
    }

    @Override//キー（コンテンツ、台番、日付）
    protected  Map<Integer,Map<Integer,Map<String,Integer>>> doInBackground(String[] string) {

        List<String> contentList = new ArrayList<String>(Arrays.asList(string));
        String hall = (String)contentList.remove(0);
        String modelno = (String)contentList.remove(0);
        Map<String,Map<Integer, Map<Integer,Integer>>> map1 = new TreeMap<>();
        for(String content:contentList){
            String url = "https://papimo.jp/h/"+ hall + "/hit/index_sort/" + modelno + "/1-20-262529/" + content;
            try {
                Document document = Jsoup.connect(url).timeout(20000).get();
                Elements elements = document.select("#table-sort tbody tr");
                Map<Integer, Map<Integer,Integer>> map2 = new TreeMap<>();
                for(Element TRelement:elements){
                    Elements TRelements = TRelement.select("td");
                    Element first = TRelements.remove(0);
                    Map<Integer,Integer> map3 = new TreeMap<>();
                    int i=0;
                    for(Element TDelement:TRelements){
                        map3.put(time[i],TDelement.text()==""?0:Integer.parseInt(TDelement.text().replace(",","")));
                        i++;
                    }
                    map2.put(Integer.parseInt(first.text()),map3);
                }
                map1.put(contentmap.get(content),map2);
            }catch (IOException e){
                System.out.println("error");
            }
        }
        Map<String,Map<Integer,Map<Integer,Integer>>> cc = ChangeMap.<String,Integer, Integer, Integer>change1(map1);
        Map<Integer,Map<String,Map<Integer,Integer>>> bb = ChangeMap.<String,Integer, Integer, Integer>change2(cc);
        Map<Integer,Map<Integer,Map<String,Integer>>> dd = ChangeMap.<Integer, String, Integer, Integer>change1(bb);

        setDatabase(dd,modelno);
        return dd;
    }

    @Override
    protected void onPostExecute( Map<Integer,Map<Integer,Map<String,Integer>>> map) {
        super.onPostExecute(map);
        callback.callbakMethod(map);
    }

    public void setInstans(Callback callback){
        this.callback = callback;
    }

    static interface Callback{
        //コンテンツ、台番、日付
        void callbakMethod( Map<Integer,Map<Integer,Map<String,Integer>>> map);
    }

    public void setDatabase(Map<Integer,Map<Integer,Map<String,Integer>>> map,String modelno){
        AppDatabase appDatabase = AppDatabaseSingleton.getInstance(context);
        for(int date:map.keySet()){
            Map<Integer, Map<String,Integer>> dateMap = map.get(date);
            for(int no:dateMap.keySet()){
                Map<String,Integer> cateMap = dateMap.get(no);
                appDatabase.mainDataDao().insert(new MainData(no,date,Integer.parseInt(modelno),cateMap.get("BB"),cateMap.get("RB"),cateMap.get("TOTAL"),cateMap.get("LAST"),cateMap.get("DIFF")));
            }
        }
    }
}