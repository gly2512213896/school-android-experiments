package com.guo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.guo.Ex4.Ex4bar1.Ex4bar1Activity;
import com.guo.Ex4.Ex4bar2.Ex4bar2Activity;
import com.guo.Ex5.Ex5Activity;

public class MainActivity extends AppCompatActivity {
    private Button btnEx4_1,btnEx4_2;
    private Button btnEx5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEx4_1=findViewById(R.id.btn_ex4bar1);
        btnEx4_2=findViewById(R.id.btn_ex4bar2);
        btnEx5=findViewById(R.id.btn_ex5);

        MyClickListener myClickListener=new MyClickListener();
        btnEx4_1.setOnClickListener(myClickListener);
        btnEx4_2.setOnClickListener(myClickListener);
        btnEx5.setOnClickListener(myClickListener);
    }
    class MyClickListener implements View.OnClickListener{
        Intent intent =null;
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_ex4bar1:
                    intent=new Intent(MainActivity.this, Ex4bar1Activity.class);
                    break;
                case R.id.btn_ex4bar2:
                    intent=new Intent(MainActivity.this, Ex4bar2Activity.class);
                    break;
                case R.id.btn_ex5:
                    intent=new Intent(MainActivity.this, Ex5Activity.class);
                    break;
            }
            startActivity(intent);
        }
    }



}