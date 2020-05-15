package com.example.myapplicationdb;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.myapplicationdb.database.MainData;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailListActivity extends AppCompatActivity {
    String hallNo;
    String[] contentNo = {"1/0","2/0","6/0","8/0","81/1"};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setTitle(intent.getStringExtra("modelname"));
        this.hallNo = "00041817";
        dataUpdate(intent);
    }


    public void dataUpdate(final Intent intent) {
        final Handler handler = new Handler();
        GetDetailDataThread getDetailDataThread = new GetDetailDataThread(hallNo, intent.getStringExtra("modeltag"), contentNo, this);
        getDetailDataThread.setCallback(new GetDetailDataThread.Callback() {
            @Override
            public void callback(final List<MainData> mainDataList) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        setContentView(R.layout.activity_detaillist);
                        ViewGroup baseLayout = findViewById(R.id.listlayout);

                        TableLayout tableLayout = new TableLayout(DetailListActivity.this);

                        TextView headdate = new TextView(DetailListActivity.this);
                        headdate.setText("DATE");
                        TextView headN = new TextView(DetailListActivity.this);
                        headN.setText("NO");
                        TextView headBB = new TextView(DetailListActivity.this);
                        headBB.setText("BB");
                        TextView headRB = new TextView(DetailListActivity.this);
                        headRB.setText("RB");
                        TextView headtotal = new TextView(DetailListActivity.this);
                        headtotal.setText("TOTAL");
                        TextView headlast = new TextView(DetailListActivity.this);
                        headlast.setText("LAST");
                        TextView headdiff = new TextView(DetailListActivity.this);
                        headdiff.setText("DIFF");

                        TableRow headRow = new TableRow(DetailListActivity.this);

                        headdate.setBackgroundResource(R.drawable.background);
                        headN.setBackgroundResource(R.drawable.background);
                        headBB.setBackgroundResource(R.drawable.background);
                        headRB.setBackgroundResource(R.drawable.background);
                        headtotal.setBackgroundResource(R.drawable.background);
                        headlast.setBackgroundResource(R.drawable.background);
                        headdiff.setBackgroundResource(R.drawable.background);

                        headdate.setTextSize(12);
                        headN.setTextSize(12);
                        headBB.setTextSize(12);
                        headRB.setTextSize(12);
                        headtotal.setTextSize(12);
                        headlast.setTextSize(12);
                        headdiff.setTextSize(12);

                        headRow.addView(headdate);
                        headRow.addView(headN);
                        headRow.addView(headBB);
                        headRow.addView(headRB);
                        headRow.addView(headtotal);
                        headRow.addView(headlast);
                        headRow.addView(headdiff);
                        tableLayout.addView(headRow);



                        for(MainData mainData:mainDataList){

                            //getLayoutInflater().inflate(R.layout.table_row,baseLayout);
                            TableRow tableRow = new TableRow(DetailListActivity.this);
                            Field[] mainDataFields = mainData.getClass().getFields();
                            List<TextView> textViews = new ArrayList<>();
                            for(Field f:mainDataFields){
                               try {
                                   String s = f.getName();
                                   if(s=="modelno"){
                                       continue;
                                   }
                                   if(f.get(mainData) instanceof Integer){
                                       TextView textView = new TextView(DetailListActivity.this);
                                       textView.setBackgroundResource(R.drawable.border);
                                       switch (f.getName()){
                                           case "date":textView.setTag(1);
                                               textView.setText(String.valueOf((Integer)f.get(mainData)).substring(4,8));
                                               textView.setWidth(125);
                                               break;
                                           case "N":textView.setTag(3);
                                               textView.setText(String.valueOf((Integer)f.get(mainData)));
                                               textView.setWidth(125);
                                               break;
                                           case "modelno":textView.setTag(2);
                                               textView.setText(String.valueOf((Integer)f.get(mainData)));
                                               textView.setWidth(125);
                                               break;
                                           case "BB":textView.setTag(4);
                                               textView.setText(String.valueOf((Integer)f.get(mainData)));
                                               textView.setWidth(125);
                                               break;
                                           case "RB":textView.setTag(5);
                                               textView.setText(String.valueOf((Integer)f.get(mainData)));
                                               textView.setWidth(125);
                                               break;
                                           case "total":textView.setTag(6);
                                               textView.setText(String.valueOf((Integer)f.get(mainData)));
                                               textView.setWidth(125);
                                               break;
                                           case "last":textView.setTag(7);
                                               textView.setText(String.valueOf((Integer)f.get(mainData)));
                                               textView.setWidth(125);
                                               break;
                                           case "diff":textView.setTag(8);
                                               textView.setText(String.valueOf((Integer)f.get(mainData)));
                                               textView.setWidth(125);
                                               break;
                                       }
                                       textViews.add(textView);
                                   }else if(f.get(mainData) instanceof String){
                                    System.out.println("s");
                                   }
                               }catch (IllegalAccessException e){
                                   System.out.println("error");
                               }
                            }
//                            List<TextView> textViews = new ArrayList<>();
//                            for(int i=0;i<7;i++){
//
//                            }
                          //  textViews.add(new TextView(DetailListActivity.this));

//                            TextView textViewN = new TextView(DetailListActivity.this);
//                            textViewN.setText(String.valueOf(mainData.N));
//                            TextView textViewdate = new TextView(DetailListActivity.this);
//                            textViewdate.setText(String.valueOf(mainData.date));
//                            TextView textViewBB = new TextView(DetailListActivity.this);
//                            textViewBB.setText(String.valueOf(mainData.BB));
//                            TextView textViewRB = new TextView(DetailListActivity.this);
//                            textViewRB.setText(String.valueOf(mainData.RB));
//                            TextView textViewtotal = new TextView(DetailListActivity.this);
//                            textViewtotal.setText(String.valueOf(mainData.total));
//                            TextView textViewlast = new TextView(DetailListActivity.this);
//                            textViewlast.setText(String.valueOf(mainData.last));
//                            TextView textViewdiff = new TextView(DetailListActivity.this);
//                            textViewdiff.setText(String.valueOf(mainData.diff));
//
//                            textViewBB.setWidth(70);
//                            textViewRB.setWidth(70);
//                            textViewtotal.setWidth(125);
//                            textViewlast.setWidth(100);
//                            textViewdiff.setWidth(125);
//                            textViewN.setWidth(100);
//
//                            textViewN.setBackgroundResource(R.drawable.border);
//                            textViewBB.setBackgroundResource(R.drawable.border);
//                            textViewRB.setBackgroundResource(R.drawable.border);
//                            textViewtotal.setBackgroundResource(R.drawable.border);
//                            textViewdate.setBackgroundResource(R.drawable.border);
//                            textViewdiff.setBackgroundResource(R.drawable.border);
//                            textViewlast.setBackgroundResource(R.drawable.border);
//
//                            tableRow.addView(textViewdate);
//                            tableRow.addView(textViewN);
//                            tableRow.addView(textViewBB);
//                            tableRow.addView(textViewRB);
//                            tableRow.addView(textViewtotal);
//                            tableRow.addView(textViewlast);
//                            tableRow.addView(textViewdiff);
                            List<TextView> l = textViews.stream()
                                    .sorted(Comparator.comparing(new Function<TextView, String>() {
                                                                     @Override
                                                                     public String apply(TextView textView) {
                                                                         return textView.getTag().toString();
                                                                     }
                                                                 })).collect(Collectors.toList());
                            for(TextView t:l){
                                tableRow.addView(t);
                            }
                            tableLayout.addView(tableRow);
                        }
                        baseLayout.addView(tableLayout);
                    }
                });
            }
        });
        getDetailDataThread.start();
    }
}
