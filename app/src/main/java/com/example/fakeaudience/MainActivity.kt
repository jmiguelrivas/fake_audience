package com.example.fakeaudience

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.grid_item.*

class MainActivity: AppCompatActivity() {
    private var mp: MediaPlayer? = null
    private var currentSong: MutableList<Int> = mutableListOf(R.raw.applauses)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        controlSound(currentSong[0])
    }

    private fun controlSound(id: Int) {
        fx_btn.setOnClickListener {
            if(mp == null) {
                mp = MediaPlayer.create(this, id)
            }
            mp?.start()
        }
    }
}