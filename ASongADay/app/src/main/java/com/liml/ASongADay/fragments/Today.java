package com.liml.ASongADay.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;

import com.cooltechworks.views.ScratchTextView;
import com.liml.ASongADay.R;


public class Today extends Fragment implements View.OnClickListener, View.OnTouchListener, OnCompletionListener, OnBufferingUpdateListener{


    private MediaPlayer mediaPlayer;
    ImageButton btnPlay;
    SeekBar sbSong;

    private final Handler handler = new Handler();

    private int mediaFileLengthInMilliseconds; // this value contains the song duration in milliseconds. Look at getDuration() method in MediaPlayer class


    public Today() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnBufferingUpdateListener(this);
        mediaPlayer.setOnCompletionListener(this);

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_one, container, false);

        //btn shit
        btnPlay = (ImageButton) view.findViewById(R.id.ibPlay);
        btnPlay.setOnClickListener(this);
        sbSong = (SeekBar) view.findViewById(R.id.sbSong);
        sbSong.setMax(99); // It means 100% .0-99
        sbSong.setOnTouchListener(this);

        //editTextSongURL = (EditText) findViewById(R.id.EditTextSongURL);
        //editTextSongURL.setText(R.string.testsong);

        ImageButton btnEmoji1= (ImageButton) view.findViewById(R.id.btnEmoji1);
        btnEmoji1.setOnClickListener(this);
        ImageButton btnEmoji2= (ImageButton) view.findViewById(R.id.btnEmoji2);
        btnEmoji2.setOnClickListener(this);
        ImageButton btnEmoji3= (ImageButton) view.findViewById(R.id.btnEmoji3);
        btnEmoji3.setOnClickListener(this);
        ImageButton btnEmoji4= (ImageButton) view.findViewById(R.id.btnEmoji4);
        btnEmoji4.setOnClickListener(this);
        ImageButton btnEmoji5= (ImageButton) view.findViewById(R.id.btnEmoji5);
        btnEmoji5.setOnClickListener(this);
        ImageButton btnEmoji6= (ImageButton) view.findViewById(R.id.btnEmoji6);
        btnEmoji6.setOnClickListener(this);

        ScratchTextView scratchTextView = new ScratchTextView(getActivity());
        scratchTextView.setRevealListener(new ScratchTextView.IRevealListener() {
            @Override
            public void onRevealed(ScratchTextView tv) {
                //on reveal
            }


            @Override
            public void onRevealPercentChangedListener(ScratchTextView stv, float percent) {
                // on text percent reveal
            }
        });

        return view;
    }

    /**
     * Method which updates the SeekBar primary progress by current song playing position
     */
    private void primarySeekBarProgressUpdater() {
        sbSong.setProgress((int) (((float) mediaPlayer.getCurrentPosition() / mediaFileLengthInMilliseconds) * 100)); // This math construction give a percentage of "was playing"/"song length"
        if (mediaPlayer.isPlaying()) {
            Runnable notification = new Runnable() {
                public void run() {
                    primarySeekBarProgressUpdater();
                }
            };
            handler.postDelayed(notification, 1000);
        }
    }



    @Override
    public void onClick(View v) {


        //
        switch (v.getId()) {
            case R.id.ibPlay:
                //btnPlay.setImageResource(R.drawable.pause);
                Toast.makeText(getActivity(),"ibPlay",Toast.LENGTH_SHORT).show();
                //
                /** ImageButton onClick event handler. Method which start/pause mediaplayer playing */
                try {
                    mediaPlayer.setDataSource("http://lookintomylife.site88.net/today/song.mp3");
                    mediaPlayer.prepare(); // you must call this method after setup the datasource in setDataSource method. After calling prepare() the instance of MediaPlayer starts load data from URL to internal buffer.
                } catch (Exception e) {
                    e.printStackTrace();
                }

                mediaFileLengthInMilliseconds = mediaPlayer.getDuration(); // gets the song length in milliseconds from URL

                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.pause);
                } else {
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.play);
                }

                primarySeekBarProgressUpdater();
                //

                break;

            case R.id.btnEmoji1:
                Toast.makeText(getActivity(),"Vote registered",Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnEmoji2:
                Toast.makeText(getActivity(),"Vote registered",Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnEmoji3:
                Toast.makeText(getActivity(),"Vote registered",Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnEmoji4:
                Toast.makeText(getActivity(),"Vote registered",Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnEmoji5:
                Toast.makeText(getActivity(),"Vote registered",Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnEmoji6:
                Toast.makeText(getActivity(),"Vote registered",Toast.LENGTH_SHORT).show();

                break;
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v.getId() == R.id.sbSong) {
             //Seekbar onTouch event handler. Method which seeks MediaPlayer to seekBar primary progress position
            if (mediaPlayer.isPlaying()) {
                SeekBar sb = (SeekBar) v;
                int playPositionInMillisecconds = (mediaFileLengthInMilliseconds / 100) * sb.getProgress();
                mediaPlayer.seekTo(playPositionInMillisecconds);
            }
        }
        return false;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        /** MediaPlayer onCompletion event handler. Method which calls then song playing is complete*/
        btnPlay.setImageResource(R.drawable.play);
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        /** Method which updates the SeekBar secondary progress by current song loading from URL position*/
        sbSong.setSecondaryProgress(percent);
    }



}
