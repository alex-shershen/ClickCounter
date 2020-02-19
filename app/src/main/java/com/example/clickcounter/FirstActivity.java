package com.example.clickcounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;


public class FirstActivity extends AppCompatActivity {
    TextView countTextView;
    ImageButton addButton;
    SharedPreferences sPref;
    Intent intent;
    Toolbar toolbar;
    boolean flagImageButton = true;
    private int count = 0;
    final String SAVED_TEXT = "saved_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countTextView = findViewById(R.id.countTextView);
        addButton = findViewById(R.id.addButton);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        intent = new Intent(this, SecondActivity.class);
        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("count", 0);
        }
        countTextView.setText(Integer.toString(count));
        loadText();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.next_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.next_activity) {
            intent.putExtra("count", countTextView.getText().toString());
            startActivity(intent);
        }
        return true;
    }


    public void addOne(View view) {
        count++;
        countTextView.setText(Integer.toString(count));
        if (flagImageButton) {
            addButton.setImageResource(R.drawable.cookie_two);
            flagImageButton = !flagImageButton;
        } else {
            addButton.setImageResource(R.drawable.cookie_one);
            flagImageButton = !flagImageButton;
        }
        Animation anim = null;
        anim = AnimationUtils.loadAnimation(this, R.anim.anim_image_button);
        addButton.startAnimation(anim);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("count", count);
        super.onSaveInstanceState(outState);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }
    void saveText() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_TEXT, countTextView.getText().toString());
        ed.apply();
    }

    void loadText() {
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        count = Integer.parseInt(savedText);
        countTextView.setText(savedText);
    }
}
