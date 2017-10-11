package com.bariski.playground

import android.util.Log
import java.util.*

class MockDataProvider {
    val data = ArrayList<User>()
    private val TAG = "MockDataProvider"

    init {

        var index = 1
        for (c in 'A'..'Z') {
            for (i in 1..10) {
                data.add(User(c + getSaltString(), index++))
            }
        }
    }

    fun size(): Int {
        return data.size
    }

    private fun getSaltString(): String {
        val SALTCHARS = "abcdefghijklmnopqrstuvwxyz"
        val salt = StringBuilder()
        val rnd = Random()
        while (salt.length < 6) { // length of the random string.
            val index = (rnd.nextFloat() * SALTCHARS.length).toInt()
            salt.append(SALTCHARS[index])
        }
        return salt.toString()

    }

    fun loadRange(startPosition: Int, count: Int): List<User> {
        Log.d(TAG, "loadRange called start=$startPosition, count=$count")
        simulateNetworkDelay()
        if (startPosition >= data.size) {
            Log.d(TAG, "loadRange executed start=$startPosition, count=0")
            return ArrayList()
        }
        var endIndex = startPosition + count
        if (endIndex >= data.size) {
            endIndex = data.size - 1
        }
        Log.d(TAG, "loadRange executed start=$startPosition, end=$endIndex")
        return data.subList(startPosition, endIndex + 1)
    }

    private fun simulateNetworkDelay() {
        Thread.sleep((Math.random() * 3).toInt() * 1000L)
    }

}
