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
    private TextView countTextView;
    private ImageButton addButton;
    private SharedPreferences sPref;
    private Intent secondActivityIntent;
    boolean flagImageButton = true;
    private String countString;
    private int count = 0;
    private static final String SAVED_TEXT = "saved_text";
    public static final String KEY_COUNT = "count";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        countTextView = findViewById(R.id.countTextView);
        addButton = findViewById(R.id.addButton);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        countString = String.valueOf(count);
        secondActivityIntent = new Intent(this, SecondActivity.class);
        if (savedInstanceState != null) {
            count = savedInstanceState.getInt(KEY_COUNT, 0);
        }
        countTextView.setText(countString);
        loadText();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                countString = String.valueOf(count);
                countTextView.setText(countString);
                if (flagImageButton) {
                    addButton.setImageResource(R.drawable.cookie_two);
                    flagImageButton = !flagImageButton;
                } else {
                    addButton.setImageResource(R.drawable.cookie_one);
                    flagImageButton = !flagImageButton;
                }
                Animation anim;
                anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_image_button);
                addButton.startAnimation(anim);
                saveText();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.next_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.next_activity) {
            secondActivityIntent.putExtra(KEY_COUNT, countTextView.getText().toString());
            startActivity(secondActivityIntent);
        }
        return true;
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
        String savedText = sPref.getString(SAVED_TEXT, "0");
        count = Integer.parseInt(savedText);
        countTextView.setText(savedText);
    }
}
