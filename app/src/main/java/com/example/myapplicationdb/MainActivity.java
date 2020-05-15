package com.example.myapplicationdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//       Thread A = new ThreadA(this);
//       A.start();
        LinearLayout linearLayout = findViewById(R.id.layout);

//        tt t = new tt(linearLayout);
//        t.setCallback(new tt.Callback() {
//            @Override
//            public void callback(LinearLayout linearLayout1) {
//                Button b = new Button(MainActivity.this);
//                b.setText("aaa");
//                linearLayout1.addView(b);
//            }
//        });
//        t.start();



        Button tigger = new Button(this);
        tigger.setText("test");
        tigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View
                                        v) {
                Intent intent = new Intent(getApplicationContext(), MachineListActivity.class);
                startActivity(intent);

//                UpdateDetail2 updatedetail = new UpdateDetail2(MainActivity.this);
//                updatedetail.execute("00041817","220020004","1/0","2/0","6/0","8/0","81/1");
//                updatedetail.setInstans(new UpdateDetail2.Callback() {
//
//
//                    @Override
//                    public void callbakMethod( Map<Integer,Map<Integer,Map<String,Integer>>> map) {
//
//                        ThreadA A = new ThreadA(MainActivity.this,map);
//                        A.setCallBack(new ThreadA.CallBack() {
//                                          @Override
//                                          public void callBack(List<MainData> mainDataList) {
//                                              Intent intent = new Intent(getApplicationContext(),DetailLIstActivity.class);
//                                              intent.putExtra("mainDataList",mainDataList);
//                                              startActivity(intent);
//
//
//                                          }
//                                      });
//                        A.start();
//
//
//                    }
//                });
            }
        });
        linearLayout.addView(tigger);


    }
}
