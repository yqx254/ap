package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.dao.DatabaseHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class QueryResult extends AppCompatActivity{
    private ListView listView;
    private RecyclerView recyclerView;
    public static final String query = "com.example.myapplication.MESSAGE";
    private DatabaseHelper helper;
    private SQLiteDatabase db;
    private ArrayList<String> queryResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Toolbar myChildToolbar = findViewById(R.id.toolbar2);
//        setSupportActionBar(myChildToolbar);
        setContentView(R.layout.query_result);
        initDatabaseHelper();
        Intent intent = getIntent();
        String s = intent.getStringExtra(MainActivity.QUERY);
        System.out.println("query string " + s);
        queryResult = search(s);
        listView = findViewById(R.id.listView2);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,queryResult));
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sendDetailId(position);
                Toast.makeText(QueryResult.this,"num" + id,Toast.LENGTH_LONG).show();
            }
        });
    }
    private void sendDetailId(int id){
        Intent intent1 = new Intent(this, DetailInfoActivity.class);
        intent1.putExtra(query,id);
        System.out.println(id);
        startActivity(intent1);
    }

    private void initDatabaseHelper(){
        helper = new DatabaseHelper(this,"myDB.db",null, 1);
        db = helper.getWritableDatabase();
    }

    private ArrayList<String> search(String query){
        ArrayList<String> result = new ArrayList<>();
        Cursor cursor = db.query("info", new String[]{"_id, name"}," name LIKE ?",
                                    new String[]{"%" + query + "%"}, null,null,null,"10");
        while(cursor.moveToNext()){
            result.add(cursor.getString(cursor.getColumnIndex("name")));
        }
        return result;
    }
}
