package com.liml.myfirstapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        TextView textView = (TextView) findViewById(R.id.messageViewer);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String message = preferences.getString("message", "");
        textView.setText(message);

    }
}
