package com.bariski.playground

import android.arch.paging.TiledDataSource


class MyTiledDataSource : TiledDataSource<User>() {
    private val TAG = "MyTiledDataSource"
    private val dataProvider = MockDataProvider()

    override fun countItems(): Int {
        return dataProvider.size()
    }

    override fun loadRange(startPosition: Int, count: Int): List<User> {
        return dataProvider.loadRange(startPosition, count)
    }
}
