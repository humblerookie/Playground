package com.bariski.playground

import android.arch.paging.PagedList
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import android.support.v7.recyclerview.extensions.DiffCallback
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val config = PagedList.Config.Builder()
                .setPageSize(10)
                .setInitialLoadSizeHint(10)
                .setEnablePlaceholders(true)
                .build()
        val pagedList = PagedList.Builder<Int, User>()
                .setConfig(config)
                .setDataSource(SequentialDataSource())
                .setMainThreadExecutor(UiThreadExecutor())
                .setBackgroundThreadExecutor(BackgroundThreadExecutor())
                .build()
        val adapter = MyPagedListAdapter(diffCallback)
        list.adapter = adapter
        adapter.setList(pagedList)


    }

    internal inner class UiThreadExecutor : Executor {
        private val mHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            mHandler.post(command)
        }
    }

    internal inner class BackgroundThreadExecutor : Executor {
        private val executorService = Executors.newFixedThreadPool(3)

        override fun execute(command: Runnable) {
            executorService.execute(command)
        }
    }


    private val diffCallback = object : DiffCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

    }
}