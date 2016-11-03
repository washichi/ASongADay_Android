package com.liml.csi_week1_v2;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        Button btnTakePicture = (Button) findViewById(R.id.btnTakePicture);
        Button btnSend = (Button) findViewById(R.id.btnGPS);
        Button btnBack = (Button) findViewById(R.id.btnBack);
        Button btnCall = (Button) findViewById(R.id.btnCall);

        btnTakePicture.setOnClickListener(btnTakePicture_handler);
        btnSend.setOnClickListener(btnSend_handler);
        btnBack.setOnClickListener(btnBack_handler);
        btnCall.setOnClickListener(btnCall_handler);
    }

    View.OnClickListener btnTakePicture_handler = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivity(intent);
        }
    };

    View.OnClickListener btnBack_handler = new View.OnClickListener() {
        public void onClick(View v) {
            finish();
        }
    };

    View.OnClickListener btnSend_handler = new View.OnClickListener() {
        public void onClick(View v) {
        }
    };

    View.OnClickListener btnCall_handler = new View.OnClickListener() {
        public void onClick(View v) {
            if (ContextCompat.checkSelfPermission(ReportActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + 113));
                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(callIntent);
            }
        }
    };
}
