package com.rsh.android.rekammedisbosimrsh.models.modelspinner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rezar on 26/05/2017.
 */

public class Tindakan {
    private String idTindakan;
    private String kategori;
    private String namaTindakan;
    private String satuan;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getIdTindakan() {
        return idTindakan;
    }

    public void setIdTindakan(String idTindakan) {
        this.idTindakan = idTindakan;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getNamaTindakan() {
        return namaTindakan;
    }

    public void setNamaTindakan(String namaTindakan) {
        this.namaTindakan = namaTindakan;
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
