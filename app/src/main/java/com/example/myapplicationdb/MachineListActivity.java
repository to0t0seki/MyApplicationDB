package com.example.myapplicationdb;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.myapplicationdb.database.AppDatabase;
import com.example.myapplicationdb.database.AppDatabaseSingleton;
import com.example.myapplicationdb.database.ModelTable;

import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

public class MachineListActivity extends AppCompatActivity {
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_machinelist);
            final ScrollView scrollView = findViewById(R.id.listlayout);
            final LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            scrollView.addView(linearLayout);
            final Handler handler = new Handler();
            ListAccessTread listAccessTread = new ListAccessTread(this);
            listAccessTread.setCallbak(new ListAccessTread.Callback() {
                @Override
                public void callback(final List<ModelTable> list) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            for (ModelTable modelTable : list) {
                                Button button = new Button(MachineListActivity.this);
                                button.setText(modelTable.modelName);
                                button.setTag(modelTable.modelNo);
                                linearLayout.addView(button);
                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Button b = (Button) v;
                                        Intent intent = new Intent(getApplicationContext(),DetailListActivity.class);
                                        intent.putExtra("modelname",b.getText());
                                        intent.putExtra("modeltag",b.getTag().toString());
                                        startActivity(intent);
                                    }
                                });
                            }
                        }
                    });
                }
            });
            listAccessTread.start();




         //   Map<String,String> map = DatabaseAccess.getMap(this);

//            ViewModelA viewModelA = ViewModelProviders.of(this).get(ViewModelA.class);
//            viewModelA.getList().observe(this, new Observer<Map<String,String>>() {
//                @Override
//                public void onChanged(Map<String,String> map) {
//                    for(String key:map.keySet()){
//                        Button button = new Button(MachineListActivity.this);
//                        button.setText(key);
//                        button.setTag(map.get(key));
//                        linearLayout.addView(button);
//
//                        button.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Button b = (Button) v;
//                                System.out.println(b.getText());
//                            }
//                        });
//                    }
//                }
//            });
        }


}

