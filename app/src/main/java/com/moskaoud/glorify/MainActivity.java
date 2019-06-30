package com.moskaoud.glorify;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private static long counter;
    private SharedPreferences mPreferenece;
    private SharedPreferences.Editor mEditor;
    TextView counterTextView;
    TextView counterButton;
    TextView counterRest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterTextView = findViewById(R.id.counter_text);
        counterButton = findViewById(R.id.counter_button);
        counterRest = findViewById(R.id.counter_reset);
        mPreferenece = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferenece.edit();

        counter = mPreferenece.getLong(getString(R.string.saved_counter_key), 0);

        counterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                counterTextView.setText(String.valueOf(counter));

            }
        });
        counterRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter = 0;
                counterTextView.setText(String.valueOf(counter));
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        mEditor.putLong(getString(R.string.saved_counter_key), counter);
        mEditor.apply();
        mEditor.commit();
    }
    @Override
    protected void onStop() {
        super.onStop();
        mEditor.putLong(getString(R.string.saved_counter_key), counter);
        mEditor.apply();
        mEditor.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mEditor.putLong(getString(R.string.saved_counter_key), counter);
        mEditor.apply();
        mEditor.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        counterTextView.setText(String.valueOf(counter));
    }

}
