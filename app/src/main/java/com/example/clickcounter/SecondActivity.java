package com.example.clickcounter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView countBigTextView = findViewById(R.id.countBigTextView);
        Intent intent = getIntent();
        String count = intent.getStringExtra("count");
        countBigTextView.setText(count);
    }
}
