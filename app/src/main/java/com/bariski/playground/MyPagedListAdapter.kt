package com.bariski.playground

import android.arch.paging.PagedListAdapter
import android.support.v7.recyclerview.extensions.DiffCallback
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MyPagedListAdapter(diffCallback: DiffCallback<User>) : PagedListAdapter<User, MyPagedListAdapter.ViewHolder>(diffCallback) {

    val TAG = "Adapter"
    override fun onBindViewHolder(holder: MyPagedListAdapter.ViewHolder?, position: Int) {
        getItem(position)?.let {
            holder?.setData(it)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyPagedListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name: TextView = view.findViewById(R.id.firstName)
        private val email: TextView = view.findViewById(R.id.email)
        fun setData(u: User) {
            name.text = u.firstName
            email.text = u.email
        }

    }
}