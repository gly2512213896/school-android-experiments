package com.guo.Ex4.Ex4bar1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.guo.MainActivity;
import com.guo.R;

public class Ex4bar1Activity extends AppCompatActivity {
    private Button btnShowCusDia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex4bar1);

        btnShowCusDia=findViewById(R.id.btn_showCusDia);
        btnShowCusDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog();
            }
        });
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
                Intent intent = new Intent(Ex4bar1Activity.this, MessageActivity.class);
                startActivity(intent);
            }
        });
        btnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Ex4bar1Activity.this, MainActivity.class));
            }
        });
    }
}