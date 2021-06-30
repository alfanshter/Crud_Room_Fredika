package com.fredika.ppm_fredika.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "laptop")
public class LaptopEntity {

    @PrimaryKey(autoGenerate = true)
    Integer id_laptop;

    @ColumnInfo(name = "nama_barang")
    String nama_barang;

    @ColumnInfo(name = "merek_barang")
    String merek_barang;

    @ColumnInfo(name = "harga_barang")
    Integer harga_barang;

    @ColumnInfo(name = "jumlah_barang")
    Integer jumlah_barang;

    @ColumnInfo(name = "email")
    String email;

    public Integer getId_laptop() {
        return id_laptop;
    }

    public void setId_laptop(Integer id_laptop) {
        this.id_laptop = id_laptop;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getMerek_barang() {
        return merek_barang;
    }

    public void setMerek_barang(String merek_barang) {
        this.merek_barang = merek_barang;
    }

    public Integer getHarga_barang() {
        return harga_barang;
    }

    public void setHarga_barang(Integer harga_barang) {
        this.harga_barang = harga_barang;
    }

    public Integer getJumlah_barang() {
        return jumlah_barang;
    }

    public void setJumlah_barang(Integer jumlah_barang) {
        this.jumlah_barang = jumlah_barang;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
