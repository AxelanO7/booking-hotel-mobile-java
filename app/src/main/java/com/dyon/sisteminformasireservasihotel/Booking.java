package com.dyon.sisteminformasireservasihotel;

public class Booking {

    private  String id;
    private String nama;
    private String jenis_kamar;
    private String nomor_ktp;
    private String kode_kamar;
    private String tglmasuk;
    private String tglkeluar;
    private String durasi;
    private String harga;
    private String subtotal;


    public Booking(String id, String nama, String jenis_kamar, String nomor_ktp, String kode_kamar, String tglmasuk, String tglkeluar, String durasi, String harga, String subtotal) {
        this.id = id;
        this.nama = nama;
        this.jenis_kamar = jenis_kamar;
        this.nomor_ktp = nomor_ktp;
        this.kode_kamar = kode_kamar;
        this.tglmasuk = tglmasuk;
        this.tglkeluar = tglkeluar;
        this.durasi = durasi;
        this.harga = harga;
        this.subtotal = subtotal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenis_kamar() {
        return jenis_kamar;
    }

    public void setJenis_kamar(String jenis_kamar) {
        this.jenis_kamar = jenis_kamar;
    }

    public String getNomor_ktp() {
        return nomor_ktp;
    }

    public void setNomor_ktp(String nomor_ktp) {
        this.nomor_ktp = nomor_ktp;
    }

    public String getKode_kamar() {
        return kode_kamar;
    }

    public void setKode_kamar(String kode_kamar) {
        this.kode_kamar = kode_kamar;
    }

    public String getTglmasuk() {
        return tglmasuk;
    }

    public void setTglmasuk(String tglmasuk) {
        this.tglmasuk = tglmasuk;
    }

    public String getTglkeluar() {
        return tglkeluar;
    }

    public void setTglkeluar(String tglkeluar) {
        this.tglkeluar = tglkeluar;
    }

    public String getDurasi() {
        return durasi;
    }

    public void setDurasi(String durasi) {
        this.durasi = durasi;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }
}
