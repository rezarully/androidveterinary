package com.rsh.android.rekammedisbosimrsh.models.modelinputrminap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rezar on 02/05/2017.
 */

public class InputRmInap {
    private String waktu;
    private String id;
    private String idRegistrasi;
    private String namaPemilik;
    private String namaHewan;
    private String diagnosis;
    private String beratBadan;
    private String namaMahasiswa;
    private String semesterMahasiswa;
    private String namaObat;
    private String jumlahResep;
    private String satuanResep;
    private String perintahPembuatan;
    private String petunjukPenggunaan;
    private String pssm;
    private String pengobatan;
    private String ketPengobatan;
    private String statusInap;
    private String foto1;
    private String foto2;
    private String foto3;
    private String foto4;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdRegistrasi() {
        return idRegistrasi;
    }

    public void setIdRegistrasi(String idRegistrasi) {
        this.idRegistrasi = idRegistrasi;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public String getNamaHewan() {
        return namaHewan;
    }

    public void setNamaHewan(String namaHewan) {
        this.namaHewan = namaHewan;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getBeratBadan() {
        return beratBadan;
    }

    public void setBeratBadan(String beratBadan) {
        this.beratBadan = beratBadan;
    }

    public String getNamaMahasiswa() {
        return namaMahasiswa;
    }

    public void setNamaMahasiswa(String namaMahasiswa) {
        this.namaMahasiswa = namaMahasiswa;
    }

    public String getSemesterMahasiswa() {
        return semesterMahasiswa;
    }

    public void setSemesterMahasiswa(String semesterMahasiswa) {
        this.semesterMahasiswa = semesterMahasiswa;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public String getJumlahResep() {
        return jumlahResep;
    }

    public void setJumlahResep(String jumlahResep) {
        this.jumlahResep = jumlahResep;
    }

    public String getSatuanResep() {
        return satuanResep;
    }

    public void setSatuanResep(String satuanResep) {
        this.satuanResep = satuanResep;
    }

    public String getPerintahPembuatan() {
        return perintahPembuatan;
    }

    public void setPerintahPembuatan(String perintahPembuatan) {
        this.perintahPembuatan = perintahPembuatan;
    }

    public String getPetunjukPenggunaan() {
        return petunjukPenggunaan;
    }

    public void setPetunjukPenggunaan(String petunjukPenggunaan) {
        this.petunjukPenggunaan = petunjukPenggunaan;
    }

    public String getPssm() {
        return pssm;
    }

    public void setPssm(String pssm) {
        this.pssm = pssm;
    }

    public String getPengobatan() {
        return pengobatan;
    }

    public void setPengobatan(String pengobatan) {
        this.pengobatan = pengobatan;
    }

    public String getKetPengobatan() {
        return ketPengobatan;
    }

    public void setKetPengobatan(String ketPengobatan) {
        this.ketPengobatan = ketPengobatan;
    }

    public String getStatusInap() {
        return statusInap;
    }

    public void setStatusInap(String statusInap) {
        this.statusInap = statusInap;
    }

    public String getFoto1() {
        return foto1;
    }

    public void setFoto1(String foto1) {
        this.foto1 = foto1;
    }

    public String getFoto2() {
        return foto2;
    }

    public void setFoto2(String foto2) {
        this.foto2 = foto2;
    }

    public String getFoto3() {
        return foto3;
    }

    public void setFoto3(String foto3) {
        this.foto3 = foto3;
    }

    public String getFoto4() {
        return foto4;
    }

    public void setFoto4(String foto4) {
        this.foto4 = foto4;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
