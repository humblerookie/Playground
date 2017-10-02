package com.bariski.playground

import android.arch.paging.KeyedDataSource
import android.os.Looper
import android.util.Log

class MyDataSource : KeyedDataSource<String, String>() {
    private val TAG = "MyDataSource"

    override fun loadBefore(currentBeginKey: String, pageSize: Int): MutableList<String> {
        Log.d(TAG, "loadBefore: thread=${Thread.currentThread()}, beginKey=$currentBeginKey, pageSize=$pageSize")
        Log.d(TAG, "isMainThread=${Looper.myLooper() == Looper.getMainLooper()}")
        val list = ArrayList<String>()
        var cur = currentBeginKey.toInt()
        Thread.sleep(2000)
        return if (cur > 1) {
            var upto = cur - pageSize
            if (upto < 1) {
                upto = 1
            }
            (cur - 1 downTo upto).mapTo(list) { it.toString() }
            list
        } else {
            list
        }
    }

    override fun getKey(item: String): String {
        Log.d(TAG, "thread=${Thread.currentThread()}, getKey: $item")
        return item
    }

    override fun loadInitial(pageSize: Int): MutableList<String> {
        Log.d(TAG, "thread=${Thread.currentThread()}, loadInitial: pagesize=$pageSize")
        Log.d(TAG, "isMainThread=${Looper.myLooper() == Looper.getMainLooper()}")
        var list = ArrayList<String>()
        (1..pageSize).mapTo(list) { it.toString() }
        return list

    }


    override fun loadAfter(currentEndKey: String, pageSize: Int): MutableList<String> {
        Log.d(TAG, "thread=${Thread.currentThread()}, loadAfter: currentEndKey=$currentEndKey, pagesize=$pageSize")
        Log.d(TAG, "isMainThread=${Looper.myLooper() == Looper.getMainLooper()}")
        Thread.sleep(2000)
        var list = ArrayList<String>()
        val index = currentEndKey.toInt() + 1
        (index..index + pageSize).mapTo(list) { it.toString() }
        return list
    }

}