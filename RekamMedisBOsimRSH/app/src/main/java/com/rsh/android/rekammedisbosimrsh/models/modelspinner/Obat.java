package com.rsh.android.rekammedisbosimrsh.models.modelspinner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rezar on 26/05/2017.
 */

public class Obat {
    private String idObat;
    private String namaObat;
    private String jenisObat;
    private String satuanObat;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getIdObat() {
        return idObat;
    }

    public void setIdObat(String idObat) {
        this.idObat = idObat;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public String getJenisObat() {
        return jenisObat;
    }

    public void setJenisObat(String jenisObat) {
        this.jenisObat = jenisObat;
    }

    public String getSatuanObat() {
        return satuanObat;
    }

    public void setSatuanObat(String satuanObat) {
        this.satuanObat = satuanObat;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
