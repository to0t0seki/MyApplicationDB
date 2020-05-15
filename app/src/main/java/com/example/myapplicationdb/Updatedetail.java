package com.example.myapplicationdb;


import android.content.Context;
import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Updatedetail extends AsyncTask<String,Void,Map<String, Map<Integer, List<Integer>>>> {
    Map<String,String> contentmap = new HashMap<>();
    Callback callback;

    Updatedetail(Context context){
        contentmap.put("81/1","DIFF");
        contentmap.put("1/0","BB");
        contentmap.put("2/0","RB");
        contentmap.put("6/0","TOTAL");
        contentmap.put("8/0","LAST");
    }

    @Override
    protected Map<String,Map<Integer, List<Integer>>> doInBackground(String[] string) {

        List<String> contentList = new ArrayList<String>(Arrays.asList(string));
        String hall = (String)contentList.remove(0);
        String machine = (String)contentList.remove(0);
        Map<String,Map<Integer, List<Integer>>> map1 = new TreeMap<>();
        for(String content:contentList){
            String url = "https://papimo.jp/h/"+ hall + "/hit/index_sort/" + machine + "/1-20-262529/" + content;
            try {
                Document document = Jsoup.connect(url).timeout(10000).get();
                Elements elements = document.select("#table-sort tbody tr");
                Map<Integer, List<Integer>> map2 = new TreeMap<>();
                for(Element TRelement:elements){
                    Elements TRelements = TRelement.select("td");
                    Element first = TRelements.remove(0);
                    List<Integer> list = new ArrayList<>();
                    for(Element TDelement:TRelements){
                        list.add(TDelement.text()==""?0:Integer.parseInt(TDelement.text().replace(",","")));
                    }
                    map2.put(Integer.parseInt(first.text()),list);
                }
                map1.put(contentmap.get(content),map2);
            }catch (IOException e){
                System.out.println("error");
            }
        }
        return map1;
    }

    @Override
    protected void onPostExecute(Map<String,Map<Integer, List<Integer>>> map) {
        super.onPostExecute(map);
        callback.callbakMethod(map);
    }

    public void setInstans(Callback callback){
        this.callback = callback;
    }

    static interface Callback{
        //コンテンツ、台番、日付
        void callbakMethod(Map<String,Map<Integer, List<Integer>>> map);
    }
}
