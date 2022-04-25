package com.guo.Ex8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.guo.R;

public class ShowMessageActivity extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_message);

        textView=findViewById(R.id.tv);
        textView.setText(getIntent().getStringExtra("etText"));

    }
}