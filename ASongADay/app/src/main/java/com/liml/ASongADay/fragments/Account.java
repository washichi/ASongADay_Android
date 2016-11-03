package com.liml.ASongADay.fragments;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.liml.ASongADay.R;
import com.liml.ASongADay.activity.GPSTracker;
import com.liml.ASongADay.activity.UserAreaActivity;


public class Account extends Fragment implements View.OnClickListener{

    Button btnAccountDetails;
    Button btnGPS;

    public Account() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_three, container, false);
        btnAccountDetails = (Button) v.findViewById(R.id.btnAccount);
        btnAccountDetails.setOnClickListener(this);

        btnGPS = (Button) v.findViewById(R.id.btnGps);
        btnGPS.setOnClickListener(this);
        return v;
    }


    @Override
    public void onClick(View v) {


        //
        switch (v.getId()) {
            case R.id.btnAccount:
                Intent userarea = new Intent(getContext(), UserAreaActivity.class);
                // ik doe nergens putextra omdat mn json object in LoginActivity staat.
                // voor het prototype heb ik dit weggelaten.
                Account.this.startActivity(userarea);
                break;
            case R.id.btnGps:
                final int MY_PERMISSION_ACCESS_COARSE_LOCATION = 11;

                if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSION_ACCESS_COARSE_LOCATION);
                }
                GPSTracker gps = new GPSTracker(getContext());
                if(gps.canGetLocation()) {
                    double latitude = gps.getLatitude(); // returns latitude
                    double longitude = gps.getLongitude(); // returns longitude
                    Toast.makeText(getContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                    gps.stopUsingGPS();
                }
                else
                {
                    gps.showSettingsAlert();
                }
                break;
        }
    }
}