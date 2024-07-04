package com.dyon.sisteminformasireservasihotel;

public class Room {
private String id, nama, kode, harga, jenis;

    public Room(String id, String nomor, String jenis, String harga, String status) {
        this.id = id;
        this.nama = nomor;
        this.kode = jenis;
        this.harga = harga;
        this.jenis = status;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getKode() {
        return kode;
    }

    public String getHarga() {
        return harga;
    }

    public String getJenis() {
        return jenis;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
}
