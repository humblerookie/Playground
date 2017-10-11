package com.bariski.playground

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class AlphabetAdapter(val listener: ItemClickListener) : RecyclerView.Adapter<AlphabetAdapter.ViewHolder>() {
    val data = ArrayList<Char>()

    init {
        data += 'A'..'Z'
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindVal(data[position], position)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_alphabet, parent, false), listener)
    }


    class ViewHolder(view: View, listener: ItemClickListener) : RecyclerView.ViewHolder(view) {
        val text: TextView = view as TextView

        init {
            text.setOnClickListener { listener.onItemClick(text.tag as Int) }
        }

        fun bindVal(value: Char, position: Int) {
            text.text = value.toString()
            text.tag = position

        }
    }

    interface ItemClickListener {
        fun onItemClick(position: Int)
    }


}