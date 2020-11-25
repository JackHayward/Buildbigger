package com.example.androidjokelibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_joke);

    TextView jokeTextView = findViewById(R.id.tv_joke_content);
    String jokeText;
    String JOKE_KEY = "joke_key";

    jokeText = getIntent().getStringExtra(JOKE_KEY);

    if (jokeText == null) {
      jokeTextView.setText("That wasn't very funny");
    } else {
      jokeTextView.setText(jokeText);
    }
  }
}