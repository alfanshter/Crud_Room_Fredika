package com.fredika.ppm_fredika.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import com.fredika.ppm_fredika.database.entity.LaptopEntity;

import java.util.List;

@Dao
public interface LaptopDao {

    @Query("INSERT INTO laptop (nama_barang,merek_barang,harga_barang,jumlah_barang,email) VALUES (:nama_barang,:merek_barang,:harga_barang,:jumlah_barang,:email)")
    void insertlaptop(String nama_barang, String merek_barang, Integer harga_barang, Integer jumlah_barang, String email);

    @Query("SELECT * FROM laptop WHERE email=:email")
    List<LaptopEntity> getall(String email);

    @Query("SELECT * FROM laptop WHERE id_laptop=:id_laptop")
    LaptopEntity get(int id_laptop);

    @Query("UPDATE laptop SET  nama_barang=:nama_barang,merek_barang=:merek_barang,jumlah_barang=:jumlah_barang,harga_barang=:harga_barang WHERE id_laptop=:id_laptop")
    void update(int id_laptop, String nama_barang, String merek_barang, Integer jumlah_barang, Integer harga_barang);

    @Delete
    void delete(LaptopEntity laptopEntity);
}
