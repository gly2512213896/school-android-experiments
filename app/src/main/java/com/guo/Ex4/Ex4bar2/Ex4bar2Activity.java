package com.guo.Ex4.Ex4bar2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.guo.MainActivity;
import com.guo.R;

public class Ex4bar2Activity extends AppCompatActivity {

    private Button btnShowHorProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex4bar2);

        btnShowHorProgress=findViewById(R.id.btn_showHorProgress);
        btnShowHorProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                horProgressDialog();
            }
        });

    }
    // 水平进度条
    private void horProgressDialog() {
        ProgressDialog horProgress = new ProgressDialog(this);
        horProgress.setIcon(R.mipmap.bilibili);
        horProgress.setTitle("网络加载");
        horProgress.setMessage("正在努力加载中，请耐心等待...");
        // 设置进度条格式
        horProgress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // 设置初始进度
        horProgress.setProgress(0);
        // 设置最大进度
        horProgress.setMax(100);
        // 设置第二进度条
        horProgress.setSecondaryProgress(3);
        horProgress.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Ex4bar2Activity.this,"点击确定",Toast.LENGTH_LONG).show();
                dialogInterface.cancel();
            }
        });
        horProgress.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(horProgress.getProgress()<horProgress.getMax()){
                    try {
                        Thread.sleep(100);
                        horProgress.incrementProgressBy(1);
                        horProgress.incrementSecondaryProgressBy(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}