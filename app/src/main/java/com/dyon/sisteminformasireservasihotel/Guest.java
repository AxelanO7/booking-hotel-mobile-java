package com.dyon.sisteminformasireservasihotel;

public class Guest {
    private String id, nama, nomorktp, alamat, jeniskelamin, nohp;

    public Guest(String id, String nama, String nomorktp, String alamat, String jeniskelamin, String nohp) {
        this.id = id;
        this.nama = nama;
        this.nomorktp = nomorktp;
        this.alamat = alamat;
        this.nohp = nohp;
        this.jeniskelamin = jeniskelamin;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNohp() {
        return nohp;
    }

    public String getNomorktp() {
        return nomorktp;
    }

    public String getJeniskelamin() {
        return jeniskelamin;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public void setNomorktp(String nomorktp) {
        this.nomorktp = nomorktp;
    }

    public void setJeniskelamin(String jeniskelamin) {
        this.jeniskelamin = jeniskelamin;
    }
}
