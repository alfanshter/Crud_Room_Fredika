package com.fredika.ppm_fredika.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.fredika.ppm_fredika.database.entity.UserEntity;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM users where email=(:email)")
    UserEntity cekuser(String email);

    @Query("INSERT INTO users (nama,email,password) VALUES (:nama,:email,:password)")
    void registerUser(String nama, String email, String password);

    @Query("SELECT * FROM users where email=(:email) and password=(:password)")
    UserEntity login(String email, String password);
}
