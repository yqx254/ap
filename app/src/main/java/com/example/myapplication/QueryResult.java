package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class QueryResult extends AppCompatActivity{
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Toolbar myChildToolbar = findViewById(R.id.toolbar2);
//        setSupportActionBar(myChildToolbar);
        setContentView(R.layout.query_result);
        Intent intent = getIntent();
        String s = intent.getStringExtra(MainActivity.QUERY);
        System.out.println("String: " + s);
        String [] mStrings = {s,s};
        listView = findViewById(R.id.listView2);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,mStrings));
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(QueryResult.this,"num" + id,Toast.LENGTH_LONG).show();
            }
        });
//        ActionBar ab = getSupportActionBar();
//        ab.setDisplayHomeAsUpEnabled(true);
    }
}
