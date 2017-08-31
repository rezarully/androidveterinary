package com.rsh.android.rekammedisbosimrsh.models.modelpasien;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rezar on 10/04/2017.
 */

public class ModelPasienSingle {
    private DataPasien dataPasien;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public DataPasien getDataPasien() {
        return dataPasien;
    }

    public void setDataPasien(DataPasien dataPasien) {
        this.dataPasien = dataPasien;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
