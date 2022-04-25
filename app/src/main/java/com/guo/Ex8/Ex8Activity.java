package com.guo.Ex8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.guo.R;

public class Ex8Activity extends AppCompatActivity {
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex8);


        btn=findViewById(R.id.btn_submit);

        Intent intent=new Intent(Ex8Activity.this,ShowMessageActivity.class);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et=findViewById(R.id.et_msg);
                String inputStr=et.getText().toString();
                intent.putExtra("etText",inputStr);
                startActivity(intent);
            }
        });
    }
}