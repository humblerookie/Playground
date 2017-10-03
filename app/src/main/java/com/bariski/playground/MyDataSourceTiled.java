package com.bariski.playground;

import android.arch.paging.TiledDataSource;

import java.util.List;


public class MyDataSourceTiled extends TiledDataSource<User> {
    @Override
    public int countItems() {
        return COUNT_UNDEFINED;
    }

    @Override
    public List<User> loadRange(int startPosition, int count) {
        return null;
    }
}
