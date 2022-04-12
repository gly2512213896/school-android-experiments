package com.guo.Ex6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.guo.R;

import java.util.ArrayList;
import java.util.List;

public class Ex6Activity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView lvCity;
    private List<City> cityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex6);

        // 设置toolbar
        toolbar = findViewById(R.id.toolbar);
        // 只能在java代码中设置空标题
        toolbar.setTitle("");
        this.setSupportActionBar(toolbar);

        lvCity = findViewById(R.id.lv_city);
        initCityList();
        lvCity.setAdapter(new CityAdapter(cityList, this));
    }

    private void initCityList() {
        cityList = new ArrayList<>();
        cityList.add(new City("北京市"));
        cityList.add(new City("上海市"));
        cityList.add(new City("湖北省"));
        cityList.add(new City("浙江省"));
        cityList.add(new City("广东省"));
        cityList.add(new City("江苏省"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.toolbar_options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, "设置了menu的item的监听事件", Toast.LENGTH_LONG).show();
        return true;
    }
}