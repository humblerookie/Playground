package com.bariski.playground

import android.arch.paging.PagedListAdapter
import android.graphics.Color
import android.support.v7.recyclerview.extensions.DiffCallback
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MyPagedListAdapter(diffCallback: DiffCallback<String>) : PagedListAdapter<String, MyPagedListAdapter.ViewHolder>(diffCallback) {

    val TAG = "Adapter"
    override fun onBindViewHolder(holder: MyPagedListAdapter.ViewHolder?, position: Int) {
        Log.d(TAG, " Is Item${getItem(position) != null}")
        getItem(position)?.let {
            holder?.setData(it)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyPagedListAdapter.ViewHolder {
        val t = TextView(parent?.context)
        t.textSize = 18f
        t.setPadding(20, 20, 20, 20)
        parent?.resources?.let { t.height = it.getDimension(R.dimen.dp75).toInt() }
        t.setTextColor(Color.BLACK)
        return ViewHolder(t)
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun setData(s: String) {
            (view as TextView).text = s
        }

    }
}