package com.guo.Ex5;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.guo.R;

import java.util.ArrayList;
import java.util.List;

/*
界面已经在fragment中设置好了，只需要加入fragmentTransaction并拿出来显示就行
 */
public class Ex5Activity extends AppCompatActivity implements MyFragmentCallBack {
    private FragmentManager fragmentManager;// 碎片管理器，用于开启事务
    private FragmentTransaction fragmentTransaction;// 碎片事务，用于执行碎片增加、显示、隐藏以及事务的提交

    // 自定义的碎片
    private AnimalFragment animalFragment;
    private PlantFragment plantFragment;
    private PersonFragment personFragment;

    private RadioGroup mRg;
    private List<Fragment> fragmentList = new ArrayList<>();

    private String tvText = "from fragment";
    private String btnText = "Click我给Activity传Data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);


        // 拿到RadioGroup的id
        mRg = findViewById(R.id.fl_rg);
        // 拿到当前碎片的管理器
        fragmentManager = getSupportFragmentManager();
        // 制作fragment，并默认先显示personFragment
        try {
            createFragments();
            hideOtherFragment(personFragment);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 为RadioGroup设置点击事件
        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int itemId) {
                try {
                    switch (itemId) {
                        case R.id.fl_rb_person:
                            hideOtherFragment(personFragment);
                            break;
                        case R.id.fl_rb_animal:
                            hideOtherFragment(animalFragment);
                            break;
                        case R.id.fl_rb_plant:
                            hideOtherFragment(plantFragment);
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void createFragments() throws Exception {
        System.out.println("进入了createFragments方法");

        if (personFragment == null) {
            personFragment = PersonFragment.newInstance(
                    "person " + tvText, btnText, R.drawable.img_person);
            // 加入fragmentList，方便后续统一隐藏
            fragmentList.add(personFragment);
        }
        if (animalFragment == null) {
            animalFragment = AnimalFragment.newInstance(
                    "animal " + tvText, btnText, R.drawable.img_animal);
            fragmentList.add(animalFragment);
        }
        if (plantFragment == null) {
            plantFragment = PlantFragment.newInstance(
                    "plant " + tvText, btnText, R.drawable.img_plant);
            fragmentList.add(plantFragment);
        }

    }

    private void hideOtherFragment(Fragment currentFragment) throws Exception {
        if (currentFragment == null) {
            createFragments();
        }
        // 开启fragment事务
        fragmentTransaction = fragmentManager.beginTransaction();
        // 是否已经加入了相应的布局中，没有就用fragmentTransaction加进去
        if (!currentFragment.isAdded()) {
            fragmentTransaction.add(R.id.fl_container, currentFragment);
        }
        // 碎片加入之后都会显示（大概），所以需要人为控制哪些需要显示哪些不要
        for (Fragment tempFragment : fragmentList) {
            // 展示当前fragment
            if (tempFragment.equals(currentFragment)) {
                fragmentTransaction.show(tempFragment);
            } else {
                fragmentTransaction.hide(tempFragment);
            }
        }
        // 提交事务，允许损失提高效率
        fragmentTransaction.commitAllowingStateLoss();
    }


    @Override
    public void setData(String str) {
        setTitle(str);
    }
}