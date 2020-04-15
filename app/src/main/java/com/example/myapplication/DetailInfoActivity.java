package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.dao.DatabaseHelper;

import java.util.HashMap;

public class DetailInfoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private DatabaseHelper helper;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_info);
        Intent intent = getIntent();
        String num = intent.getStringExtra(QueryResult.query);
        initDatabaseHelper();
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view1);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        HashMap<String, String> detail = getDetailInfo(num);
        String [] myData = {
                "ID :" + detail.get("id"),
                "姓名 :" + detail.get("name") ,
                "年龄  : " + detail.get("age"),
                "住址 : " + detail.get("address"),
                "此处还有其他字段和描述"
        };
        myAdapter = new MyAdapter(myData);
        recyclerView.setAdapter(myAdapter);
    }
    private void initDatabaseHelper(){
        helper = new DatabaseHelper(this,"myDB.db",null, 1);
        db = helper.getWritableDatabase();
    }
    private HashMap<String, String> getDetailInfo(String id){
        HashMap<String, String> result = new HashMap<>();
        Cursor cursor = db.query("info",new String[]{"_id","name","age","address"},"_id = ?",new String[]{id},null,null,null,"1");
        while(cursor.moveToNext()){
            result.put("id", cursor.getString(cursor.getColumnIndex("_id")));
            result.put("name", cursor.getString(cursor.getColumnIndex("name")));
            result.put("age", cursor.getString(cursor.getColumnIndex("age")));
            result.put("address", cursor.getString(cursor.getColumnIndex("address")));
        }
        return result;
    }
}
