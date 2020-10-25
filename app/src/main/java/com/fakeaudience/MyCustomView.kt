package com.fakeaudience

import android.content.Context
import android.media.MediaPlayer
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class MyCustomView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    data class Fx(var title: String, var icon:String, var audio:Int)
    private val Fxs = listOf<Fx>(
            Fx("Applause","\uf2b5", R.raw.applause),
            Fx("Aww!","\uf2b5", R.raw.applause),
            Fx("Boo","\uf2b5", R.raw.applause),
            Fx("Burp","\uf2b5", R.raw.applause),
            Fx("Crickets","\uf2b5", R.raw.applause),
            Fx("Drumroll","\uf2b5", R.raw.applause),
            Fx("Evil Laughter", "\uf2b5", R.raw.applause),
            Fx("Fart","\uf2b5", R.raw.applause),
            Fx("Laughs","\uf2b5", R.raw.applause),
            Fx("Meow!","\uf2b5", R.raw.applause),
            Fx("Rimshot","\uf2b5", R.raw.applause),
            Fx("Thunderstorm","\uf2b5", R.raw.applause),
            Fx("Waves","\uf2b5", R.raw.applause),
            Fx("Nevada Dream","\uf2b5", R.raw.applause)
    )
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

    private fun playSound(position:Int) {
        selectedItems.removeAt(position)
        refreshData(false)
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
                playSound(position)
            }
            return view
        }
    }
}