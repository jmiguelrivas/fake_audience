package com.fakeaudience

import android.content.Context
import android.media.MediaPlayer
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class MyCustomView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    data class Fx(var title: String, var icon:String, var audio:Int, var backgroudNoise:Boolean)
    private val Fxs = listOf<Fx>(
        Fx("Angry Cat","\ue912", R.raw.angry_cat, false),
        Fx("Applause","\ue914", R.raw.applause, false),
        Fx("Aww!","\ue913", R.raw.aww, false),
        Fx("Boo","\ue915", R.raw.boo, false),
        Fx("Burp","\ue904", R.raw.burp, false),
        Fx("Crickets","\ue90a", R.raw.crickets, false),
        Fx("Drum Roll","\ue911", R.raw.drum_roll, false),
        Fx("Evil Laugh", "\ue903", R.raw.evil_laugh, false),
        Fx("Fart","\ue902", R.raw.fart, false),
        Fx("Laughing Crowd","\ue90b", R.raw.laughing_crowd, false),
        Fx("Rimshot","\ue900", R.raw.rimshot, false),
        Fx("Birds", "\ue909", R.raw.birds, true),
        Fx("Coffe Shop", "\ue910", R.raw.coffe_shop, true),
        Fx("Rain", "\ue906", R.raw.rain, true),
        Fx("Thunders","\ue907", R.raw.thunder, true),
        Fx("Ocean Waves","\ue908", R.raw.ocean_waves, true),
        Fx("Wind", "\ue916", R.raw.wind, true),
        Fx("Nevada Dream","\ue905", R.raw.applause, false)
    )
    private var mp: MediaPlayer? = null
    private var currentSong: Int? = null
    private var listview: ListView
    private var selectedItems: MutableList<Fx> = ArrayList()

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.my_custom_view_layout, this, true)
        listview = view.findViewById(R.id.listview)
        selectedItems.addAll(Fxs)
        refreshData(true)
    }

    fun refreshData(clearData: Boolean) {
        listview.adapter = MyCustomViewAdapter(context, R.layout.my_custom_view_item, selectedItems)
    }

    private fun play(song: Int){
        mp = MediaPlayer.create(context, song)
        mp?.start()
    }

    private fun stop() {
        mp?.stop()
        mp?.reset()
        mp?.release()
        mp = null
    }

    private fun playSound(item:Fx, position:Int) {
        val isNotStarted = mp == null
        val isPlayingADifferentSong = position != currentSong && mp != null
        val isBackgroundNoise = item.backgroudNoise

        if(isNotStarted){
            play(item.audio)
        } else if(isPlayingADifferentSong) {
            stop()
            play(item.audio)
        } else {
            stop()
        }

        if(isBackgroundNoise) {
            mp?.setLooping(true)
        } else {
            mp?.setLooping(false)
        }
        currentSong = position
    }

    inner class MyCustomViewAdapter(context: Context?, var resource: Int, var objects: MutableList<Fx>?) : ArrayAdapter<Fx>(context, resource, objects) {
        private val inflater: LayoutInflater = LayoutInflater.from(context)
        override fun getCount(): Int {
            return objects!!.size
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view = inflater.inflate(resource, parent, false)
            val play_icon = view.findViewById<TextView>(R.id.play_icon)
            val play_label = view.findViewById<TextView>(R.id.play_label)
            val play_item = view.findViewById<LinearLayout>(R.id.play_item)

            play_icon.text = objects!!.get(position).icon
            play_label.text = objects!!.get(position).title

            play_item.setOnClickListener {
                playSound(objects!!.get(position), position)
            }
            return view
        }
    }
}