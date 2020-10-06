package com.example.fakeaudience

import android.media.MediaPlayer
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.Button
import android.widget.TextView

class GridItem(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    data class Fx(var title: String, var icon:String, var audio:Any)

    private lateinit var mp: MediaPlayer
    private val fx_title: TextView
    private val fx_btn: Button
    private val Fxs = listOf<Fx>(
        Fx("Applauses","\uf2b5", R.raw.applauses),
        Fx("Crickets","\uf2b5", R.raw.applauses),
        Fx("Boo","\uf2b5", R.raw.applauses),
        Fx("Aww!","\uf2b5", R.raw.applauses),
        Fx("Rimshot","\uf2b5", R.raw.applauses),
        Fx("Laughs","\uf2b5", R.raw.applauses),
        Fx("Meow!","\uf2b5", R.raw.applauses),
        Fx("Burp","\uf2b5", R.raw.applauses),
        Fx("Fart","\uf2b5", R.raw.applauses),
        Fx("Drumroll","\uf2b5", R.raw.applauses),
        Fx("Evil Laughter", "\uf2b5", R.raw.applauses)
    )

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.grid_item, this, true)
        fx_title = view.findViewById(R.id.fx_title)
        fx_btn = view.findViewById(R.id.fx_btn)
        fx_title.text = Fxs[5].title
        fx_btn.text = Fxs[5].icon
//        mp = MediaPlayer.create(R.layout.activity_main, Fxs[5].audio)
    }

//    fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.grid_item)
//        mp = MediaPlayer.create(this, R.raw.applauses)
//        mp.isLooping = false
//        mp.setVolume(1.0f, 1.0f)
//    }

    fun playApplauses(v: View) {
        if (mp.isPlaying) {
            mp.pause()
        } else {
            mp.seekTo(0);
            mp.start()
        }
    }
}