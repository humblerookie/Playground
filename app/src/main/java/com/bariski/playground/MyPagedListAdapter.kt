package com.bariski.playground

import android.arch.paging.PagedListAdapter
import android.support.v7.recyclerview.extensions.DiffCallback
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView

class MyPagedListAdapter(diffCallback: DiffCallback<User>) : PagedListAdapter<User, MyPagedListAdapter.ViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: MyPagedListAdapter.ViewHolder?, position: Int) {
        holder?.setData(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyPagedListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name: TextView = view.findViewById(R.id.firstName)
        private val progress: ProgressBar = view.findViewById(R.id.progress)
        fun setData(u: User?) {
            if (u == null) {
                //Show loader, item is being loaded
                name.visibility = View.GONE
                progress.visibility = View.VISIBLE
            } else {
                name.text = u.name
                name.visibility = View.VISIBLE
                progress.visibility = View.GONE
            }

        }

    }
}