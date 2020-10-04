package com.example.fakeaudience

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    MediaPlayer fxApplauses;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fxApplauses=MediaPlayer.create(MainActivity.this, R.raw.applauses)
    }
    public void playApplauses(View v){
        fxApplauses.start();
    }
}