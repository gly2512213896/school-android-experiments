package com.guo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnEx4_1,btnEx4_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEx4_1=findViewById(R.id.btn_customDia);
        btnEx4_2=findViewById(R.id.btn_horDia);
        MyClickListener myClickListener=new MyClickListener();

        btnEx4_1.setOnClickListener(myClickListener);
        btnEx4_2.setOnClickListener(myClickListener);
    }
    class MyClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_customDia:
                    customDialog();
                    break;
                case R.id.btn_horDia:
                    horiProgressDialog();
                    break;
            }
        }
    }

    private void customDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_customdia, null, false);
        AlertDialog.Builder customDia = new AlertDialog.Builder(this, R.style.ex4_1);
        Button btnPositive = view.findViewById(R.id.btn_positive);
        Button btnNegative = view.findViewById(R.id.btn_negative);
        customDia.setView(view).show();
        btnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MessageActivity.class);
                startActivity(intent);
            }
        });
        btnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MainActivity.class));
            }
        });
    }
    // 水平进度条
    private void horiProgressDialog() {
        ProgressDialog horiProgress = new ProgressDialog(this);
        horiProgress.setIcon(R.mipmap.bilibili);
        horiProgress.setTitle("网络加载");
        horiProgress.setMessage("正在努力加载中，请耐心等待...");
        // 设置进度条格式
        horiProgress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // 设置初始进度
        horiProgress.setProgress(0);
        // 设置最大进度
        horiProgress.setMax(100);
        // 设置第二进度条
        horiProgress.setSecondaryProgress(3);
        horiProgress.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this,"点击确定",Toast.LENGTH_LONG).show();
                dialogInterface.cancel();
            }
        });
        horiProgress.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(horiProgress.getProgress()<horiProgress.getMax()){
                    try {
                        Thread.sleep(100);
                        horiProgress.incrementProgressBy(1);
                        horiProgress.incrementSecondaryProgressBy(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}