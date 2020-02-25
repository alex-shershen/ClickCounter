package com.example.clickcounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private TextView countTextView;
    private ImageButton addButton;
    private SharedPreferences countClickSharedPref;
    private Intent secondActivityIntent;
    boolean flagImageButton = true;
    private String countClickString;
    private int countClick = 0;
    private static final String SAVED_TEXT = "saved_text";
    public static final String KEY_COUNT = "countClick";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countTextView = findViewById(R.id.countTextView);
        addButton = findViewById(R.id.addButton);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        countClickString = String.valueOf(countClick);
        secondActivityIntent = new Intent(this, ResultActivity.class);
        if (savedInstanceState != null) {
            countClick = savedInstanceState.getInt(KEY_COUNT, 0);
        }
        countTextView.setText(countClickString);
        loadText();
        addButton.setOnClickListener(v -> {
            countClick++;
            countClickString = String.valueOf(countClick);
            countTextView.setText(countClickString);
            updateButtonImage();
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
        outState.putInt(KEY_COUNT, countClick);
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onPause() {
        super.onPause();
        saveText();
    }

    private void updateButtonImage(){
        if (flagImageButton) {
            addButton.setImageResource(R.drawable.cookie_two);
        } else {
            addButton.setImageResource(R.drawable.cookie_one);
        }
        flagImageButton = !flagImageButton;
        Animation anim;
        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_image_button);
        addButton.startAnimation(anim);
    }

    private void saveText() {
        countClickSharedPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor countClickEditor = countClickSharedPref.edit();
        countClickEditor.putString(SAVED_TEXT, countTextView.getText().toString());
        countClickEditor.apply();
    }

    private void loadText() {
        countClickSharedPref = getPreferences(MODE_PRIVATE);
        String countSavedText = countClickSharedPref.getString(SAVED_TEXT, "0");
        countClick = Integer.parseInt(countSavedText);
        countTextView.setText(countSavedText);
    }
}
