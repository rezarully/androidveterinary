package com.rsh.android.rekammedisbosimrsh.models.modelspinner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rezar on 26/05/2017.
 */

public class Lab {
    private String idLab;
    private String kategori;
    private String namaPemeriksaan;
    private String satuan;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getIdLab() {
        return idLab;
    }

    public void setIdLab(String idLab) {
        this.idLab = idLab;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getNamaPemeriksaan() {
        return namaPemeriksaan;
    }

    public void setNamaPemeriksaan(String namaPemeriksaan) {
        this.namaPemeriksaan = namaPemeriksaan;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
