package com.liml.csi_week1_v2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnReport = (Button) findViewById(R.id.btnReport);
        btnReport.setOnClickListener(btnReport_handler);
    }

    View.OnClickListener btnReport_handler = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, ReportActivity.class);
            startActivity(intent);
        }
    };

}
