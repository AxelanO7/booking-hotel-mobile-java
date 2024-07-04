package com.dyon.sisteminformasireservasihotel;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String database_name = "db_hotel";
    private static final int database_version = 1;

    public DatabaseHelper(Context context){
        super(context,database_name,null,database_version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String ct1 = "CREATE TABLE tb_booking (id INTEGER PRIMARY KEY AUTOINCREMENT, nama TEXT NOT NULL, jenis_kamar TEXT NOT NULL, nomor_ktp TEXT NOT NULL, kode_kamar TEXT NOT NULL, tanggal_masuk INTEGER, tanggal_keluar TEXT NOT NULL, durasi TEXT NOT NULL, harga TEXT NOT NULL, subtotal TEXT NOT NULL)";
        db.execSQL(ct1);
        String ct2 = "CREATE TABLE tb_guest (id INTEGER PRIMARY KEY AUTOINCREMENT, nama TEXT NOT NULL, nomor_ktp TEXT NOT NULL, alamat TEXT NOT NULL, jenis_kelamin TEXT NOT NULL, no_hp TEXT NOT NULL)";
        db.execSQL(ct2);
        String ct3 = "CREATE TABLE tb_room (id INTEGER PRIMARY KEY AUTOINCREMENT, nama TEXT NOT NULL, kode_kamar TEXT NOT NULL, jenis_kamar TEXT NOT NULL, harga TEXT NOT NULL)";
        db.execSQL(ct3);

        String it1 = "INSERT into tb_booking (id, nama, jenis_kamar, nomor_ktp, kode_kamar, tanggal_masuk, tanggal_keluar, durasi, harga, subtotal) VALUES (1, 'Made', 'Single', '1234567890', 'K1', '2020-12-01', '2020-12-02', '1', '100000', '100000');" ;
        db.execSQL(it1);
        String it2 = "INSERT into tb_guest (id, nama, nomor_ktp, alamat, jenis_kelamin, no_hp) VALUES (1, 'Made', '1234567890', 'Denpasar', 'Laki-laki', '1234567890');";
        db.execSQL(it2);
        String it3 = "INSERT into tb_room (id, nama, kode_kamar, jenis_kamar, harga) VALUES (1, 'Kamar 1', 'K1', 'Single', '100000');";
        db.execSQL(it3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
