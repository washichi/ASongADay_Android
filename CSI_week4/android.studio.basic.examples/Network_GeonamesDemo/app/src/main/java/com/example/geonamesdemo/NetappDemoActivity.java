package com.example.geonamesdemo;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;


public class NetappDemoActivity extends Activity {
    //private static final String JSONurl = "http://api.geonames.org/findNearbyWikipediaJSON?formatted=true&lat=51.511&lng=5.616&username=xxxx&password=xxxx";
    private static final String JSONurl = "https://raw.githubusercontent.com/b3nzchr3ur/android.examples/master/workspace.android.basic/Network_GeonamesDemo/findNearbyWikipediaJSON.json";
    private static final String TAG="FHICT";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button button = (Button)findViewById(R.id.retrieveJSON);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                retrieveFromNetwork();
            }
        });

        button = (Button)findViewById(R.id.buttonNetworkStatus);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                showNetworkStatus();
            }
        });
    }

    private void retrieveFromNetwork() {

        Response.Listener<JSONObject> successListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String responseAsString = parseJsonObjectToString(response);
                displayOnScreen(responseAsString);
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                displayOnScreen("Failed to retrieved data: " + error.getLocalizedMessage());
            }
        };

        String url = JSONurl; // http://www.somesite.com/somejson.json
        int method = Request.Method.GET;
        JSONObject request = null;

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(method, url, request,
                successListener, errorListener);

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsObjRequest);
    }

    private String parseJsonObjectToString(JSONObject jsonObject) {
        String message = "";
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("geonames");

            int numberOfPosts = jsonArray.length();
            for(int messageIndex = 0; messageIndex < numberOfPosts; messageIndex++) {
                JSONObject jsonObjectMessage = jsonArray.getJSONObject(messageIndex);

                message += String.format("[%d] - %s\n\n", messageIndex, jsonObjectMessage.getString("summary"));
            }
        } catch (JSONException e) {
            message = "JSONException";
            appendExceptionToLog(message,e);
        }

        return message;
    }

    private void appendExceptionToLog(String message, Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        Log.d(TAG, sw.toString());
    }

    private void displayOnScreen(String message) {
        TextView outputView = (TextView)findViewById(R.id.editTextReply);
        outputView.setText(message);
    }

    private void showNetworkStatus() {
        ConnectivityManager connectivityManager=
                (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null) {
            displayOnScreen(networkInfo.getState() + " to " +
                    networkInfo.getTypeName());
        } else {
            displayOnScreen("No network available.");
        }
    }
}