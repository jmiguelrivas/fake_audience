package com.example.fakeaudience

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    private lateinit var mp: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mp = MediaPlayer.create(this, R.raw.applauses)
        mp.isLooping = false
        mp.setVolume(1.0f, 1.0f)
    }

    fun playApplauses(v: View) {
        if (mp.isPlaying) {
            mp.pause()
        } else {
            mp.seekTo(0);
            mp.start()
        }
    }
}