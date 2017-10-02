package com.bariski.playground

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.recyclerview.extensions.DiffCallback
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val data = ArrayList<String>()
        (1..30).mapTo(data) { it.toString() }
        /*val observer = object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                list.clipToPadding = false
                val pivot = (list.left + list.right) / 2
                val padd = (pivot - resources.getDimension(R.dimen.dp75)).toInt()
                list.setPadding(padd, 0, padd, 0)
                val listener = CarouselListener(pivot)
                list.addOnScrollListener(listener)
                list.adapter = RcAdapter(pivot, list, list.layoutManager as LinearLayoutManager)
                list.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        }
        list.viewTreeObserver.addOnGlobalLayoutListener(observer)*/


    }

    val DIFF_CALLBACK = object : DiffCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem.equals(newItem)
        }
    }
}
