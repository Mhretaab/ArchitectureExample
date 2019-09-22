package com.example.architectureexample.user;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.architectureexample.common.db.BaseDao;

import io.reactivex.Single;

@Dao
public abstract class UserDao implements BaseDao<User> {

    @Query("SELECT * FROM user WHERE email = :email")
    abstract Single<User> findByEmail(String email);

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    abstract Single<User> findByEmail(String email, String password);

    @Query("SELECT * FROM user WHERE uuid = :uuid")
    abstract Single<User> findByUuid(String uuid);
}
