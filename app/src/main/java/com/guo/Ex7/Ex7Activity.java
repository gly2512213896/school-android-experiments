package com.guo.Ex7;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.guo.R;

import java.util.ArrayList;
import java.util.List;


public class Ex7Activity extends AppCompatActivity {

    private TabLayout tabLayout;
    private List<IconRelation> iconRelationList;
    private List<Fragment> fragmentList;
    private ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex7);

        tabLayout = findViewById(R.id.tl);
        viewPager2=findViewById(R.id.vp2);

        initIcon();
        initFragment();


        // ViewPager2设置adapter，在pager中，通过adapter返回合适的fragmentList给ViewPager
        viewPager2.setAdapter(new ImageStateAdapter(this,fragmentList));

        // tabLayout只是一个容器，里面还没放tab
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                // 为每个tab设置view，拿到图标；getTabView方法中只设置了图片源（通过initIcon方法设置的选择器），没设置字体
                tab.setCustomView(getTabView(iconRelationList,position));
            }
        }).attach();// 将tabLayout和viewPager2连接起来


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view = tab.getCustomView();

                ImageView imageView = view.findViewById(R.id.tab_iv);
                TextView textView = view.findViewById(R.id.tab_tv);
                // 设置tab中组件的选中状态
                imageView.setSelected(true);

                // 设置选中时的字体
                textView.setTextAppearance(R.style.TabSelectedStyle);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();

                ImageView imageView = view.findViewById(R.id.tab_iv);
                TextView textView = view.findViewById(R.id.tab_tv);
                imageView.setSelected(false);

                textView.setTextAppearance(R.style.TabUnSelectedStyle);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        // 设置默认选中
        tabLayout.getTabAt(0).select();
    }

    // 初始化fragment给ViewPager用
    private void initFragment() {
        fragmentList = new ArrayList<>();

        fragmentList.add(WeatherFragment.newInstance(R.drawable.sunnyday,"晴天"));
        fragmentList.add(WeatherFragment.newInstance(R.drawable.windyday,"多云"));
        fragmentList.add(WeatherFragment.newInstance(R.drawable.rainyday,"雨天"));
    }

    // 初始化Icon给Tab用
    private void initIcon() {

        iconRelationList = new ArrayList<>();
        iconRelationList.add(new IconRelation(R.drawable.sel_sunday_ic, "晴天"));
        iconRelationList.add(new IconRelation(R.drawable.sel_windyday_ic, "多云"));
        iconRelationList.add(new IconRelation(R.drawable.sel_rainyday_ic, "雨天"));
    }

    // 用来设置tab的view
    private View getTabView(List<IconRelation> data, int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.tab_item, null);

        ImageView imageView = view.findViewById(R.id.tab_iv);
        TextView textView = view.findViewById(R.id.tab_tv);

        imageView.setImageResource(data.get(position).getImageId());
        textView.setText(data.get(position).getName());

        return view;
    }
}