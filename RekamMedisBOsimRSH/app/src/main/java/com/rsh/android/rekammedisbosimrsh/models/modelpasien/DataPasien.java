package com.rsh.android.rekammedisbosimrsh.models.modelpasien;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rezar on 30/03/2017.
 */

public class DataPasien {
    private String waktu;
    private String id;
    private String noRegistrasi;
    private String noRm;
    private String namaPemilik;
    private String namaHewan;
    private String jenisHewan;
    private String signalemenTtl;
    private String signalemenKelamin;
    private String username;
    private String statusAntrian;
    private String fotoProfil;
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

    public String getNoRegistrasi() {
        return noRegistrasi;
    }

    public void setNoRegistrasi(String noRegistrasi) {
        this.noRegistrasi = noRegistrasi;
    }

    public String getNoRm() {
        return noRm;
    }

    public void setNoRm(String noRm) {
        this.noRm = noRm;
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

    public String getJenisHewan() {
        return jenisHewan;
    }

    public void setJenisHewan(String jenisHewan) {
        this.jenisHewan = jenisHewan;
    }

    public String getSignalemenTtl() {
        return signalemenTtl;
    }

    public void setSignalemenTtl(String signalemenTtl) {
        this.signalemenTtl = signalemenTtl;
    }

    public String getSignalemenKelamin() {
        return signalemenKelamin;
    }

    public void setSignalemenKelamin(String signalemenKelamin) {
        this.signalemenKelamin = signalemenKelamin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatusAntrian() {
        return statusAntrian;
    }

    public void setStatusAntrian(String statusAntrian) {
        this.statusAntrian = statusAntrian;
    }

    public String getFotoProfil() {
        return fotoProfil;
    }

    public void setFotoProfil(String fotoProfil) {
        this.fotoProfil = fotoProfil;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
