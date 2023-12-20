package com.example.smarthome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import com.example.smarthome.MyAdapter.*;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    private RecyclerView mRecylerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mRecylerView=findViewById(R.id.recycler_view);
        mRecylerView.setLayoutManager(new LinearLayoutManager(this));
        mRecylerView.setAdapter(new MyAdapter(this,getData()));
    }
    private List<String> getData(){
        //返回一个包含多个字符串的列表，用于设置RecyclerView的数据源
        List<String> data=new ArrayList<>();
        //
        return data;
    }
}