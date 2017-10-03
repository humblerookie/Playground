package com.bariski.playground

import android.arch.paging.KeyedDataSource
import android.os.Looper
import android.util.Log

class SequentialDataSource : KeyedDataSource<Int, User>() {

    private val TAG = "SequentialDataSource"

    override fun loadInitial(pageSize: Int): MutableList<User> {
        Log.d(TAG, "thread=${Thread.currentThread()}, loadInitial: pagesize=$pageSize")
        Log.d(TAG, "isMainThread=${Looper.myLooper() == Looper.getMainLooper()}")

        return MockDataProvider.loadAfter(0, pageSize)
    }

    override fun loadBefore(currentBeginKey: Int, pageSize: Int): MutableList<User> {
        Log.d(TAG, "loadBefore: thread=${Thread.currentThread()}, beginKey=$currentBeginKey, pageSize=$pageSize")
        Log.d(TAG, "isMainThread=${Looper.myLooper() == Looper.getMainLooper()}")

        return MockDataProvider.loadBefore(currentBeginKey, pageSize)
    }

    override fun getKey(item: User): Int {
        return item.id
    }

    override fun loadAfter(currentEndKey: Int, pageSize: Int): MutableList<User> {
        Log.d(TAG, "thread=${Thread.currentThread()}, loadAfter: currentEndKey=$currentEndKey, pagesize=$pageSize")
        Log.d(TAG, "isMainThread=${Looper.myLooper() == Looper.getMainLooper()}")

        return MockDataProvider.loadAfter(currentEndKey, pageSize)
    }
}