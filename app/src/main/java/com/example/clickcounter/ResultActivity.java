package com.example.clickcounter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import static com.example.clickcounter.MainActivity.KEY_COUNT;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView countBigTextView = findViewById(R.id.count_big_textview);
        Intent intent = getIntent();
        String count = intent.getStringExtra(KEY_COUNT);
        countBigTextView.setText(count);
    }
}
