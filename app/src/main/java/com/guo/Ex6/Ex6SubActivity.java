package com.guo.Ex6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.GridView;

import com.guo.R;

import java.util.ArrayList;
import java.util.List;

public class Ex6SubActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private GridView gvCity;
    private List<SubCity> subCityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex6_sub);

        // 设置toolbar
        toolbar=findViewById(R.id.toolbar);
        // 只能在java代码中设置空标题
        toolbar.setTitle("");
        this.setSupportActionBar(toolbar);

        gvCity=findViewById(R.id.gv_city);
        initSubCityList();
        gvCity.setAdapter(new SubCityAdapter(subCityList,this));
    }
    private void initSubCityList(){
        subCityList=new ArrayList<>();
        subCityList.add(new SubCity("昌平区"));
        subCityList.add(new SubCity("朝阳区"));
        subCityList.add(new SubCity("大兴区"));
        subCityList.add(new SubCity("东城区"));
        subCityList.add(new SubCity("房山区"));
        subCityList.add(new SubCity("丰台区"));
        subCityList.add(new SubCity("海淀区"));
        subCityList.add(new SubCity("怀柔区"));
        subCityList.add(new SubCity("门头口区"));
    }
}