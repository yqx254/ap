package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.text.TextUtils;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnQueryTextListener{
    public static final String QUERY = "com.example.myapplication.MESSAGE";
    private SearchView sView;
    private ListView lView;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String [] mStrings = {"大中锋","中锋","中风"};
        sView = (SearchView)findViewById(R.id.search);
        lView = (ListView)findViewById(R.id.linearLayout1);

        lView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,mStrings));
        lView.setTextFilterEnabled(true);

//        sView.setIconified(false);
        sView.setOnQueryTextListener(this);
        sView.setSubmitButtonEnabled(true);
        sView.setQueryHint("请输入姓名或电话");
    }
    @Override
    public boolean onQueryTextChange(String newText){
        if(TextUtils.isEmpty(newText)) {
            //清除listview的过滤
            lView.clearTextFilter();
        }
        else {
            //用用户输入的内容对listview的列表项进行过滤
            lView.setFilterText(newText);
        }
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        //这里仅仅是模拟，实际中应该在该方法中进行查询，然后将结果得到
        Toast.makeText(MainActivity.this, "查询中："+query, Toast.LENGTH_SHORT).show();
        this.sendQuery(query);
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /*提交查询*/
    private void sendQuery(String query){
        Intent intent = new Intent(this, QueryResult.class);
        intent.putExtra(QUERY, query);
        startActivity(intent);
    }
}
