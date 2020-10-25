package com.fakeaudience

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class MyCustomView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var listview: ListView
    private var title: TextView
    private var play_btn: ImageView

    private var selectedItems: MutableList<String> = ArrayList()

    data class Fx(var title: String, var icon:String, var audio:Int)
    val Fxs = listOf<Fx>(
            Fx("Applause","\uf2b5", R.raw.applauses),
            Fx("Crickets","\uf2b5", R.raw.applauses),
            Fx("Boo","\uf2b5", R.raw.applauses),
            Fx("Aww!","\uf2b5", R.raw.applauses),
            Fx("Laughs","\uf2b5", R.raw.applauses),
            Fx("Meow!","\uf2b5", R.raw.applauses),
            Fx("Evil Laughter", "\uf2b5", R.raw.applauses),
            Fx("Rimshot","\uf2b5", R.raw.applauses),
            Fx("Burp","\uf2b5", R.raw.applauses),
            Fx("Fart","\uf2b5", R.raw.applauses),
            Fx("Drumroll","\uf2b5", R.raw.applauses)
    )

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.my_custom_view_layout, this, true)
        listview = view.findViewById(R.id.listview)
        title = view.findViewById(R.id.title)
        play_btn = view.findViewById(R.id.play_btn)

        for (item in Fxs) {
            selectedItems.add(0, item.title)
        }
        refreshData(true)
    }

    fun refreshData(clearData: Boolean) {
        listview.adapter = MyCustomViewAdapter(context, R.layout.my_custom_view_item, selectedItems)
    }

    private fun playSound(position:Int) {
        selectedItems.removeAt(position)
        refreshData(false)
    }

    inner class MyCustomViewAdapter(context: Context?, var resource: Int, var objects: MutableList<String>?) : ArrayAdapter<String>(context, resource, objects) {
        private val inflater: LayoutInflater = LayoutInflater.from(context)
        override fun getCount(): Int {
            return objects!!.size
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view = inflater.inflate(resource, parent, false)
            val name = view.findViewById<TextView>(R.id.name)
            val delete = view.findViewById<ImageView>(R.id.delete)
            name.text = objects!!.get(position)
            delete.setOnClickListener {
                playSound(position)
            }
            return view
        }
    }
}