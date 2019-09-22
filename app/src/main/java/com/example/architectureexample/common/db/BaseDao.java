package com.example.architectureexample.common.db;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<Long> insert(T t);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertAll(T... t);

    @Update
    Single<Integer> update(T t);

    @Delete
    Completable delete(T t);

    @Delete
    Completable deleteAll(T... t);
}
