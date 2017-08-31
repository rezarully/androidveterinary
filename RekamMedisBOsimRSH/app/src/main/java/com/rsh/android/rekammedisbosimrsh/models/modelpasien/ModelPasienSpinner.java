package com.rsh.android.rekammedisbosimrsh.models.modelpasien;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rezar on 01/05/2017.
 */

public class ModelPasienSpinner {
    private List<DataPasien> dataPasien = new ArrayList<DataPasien>();;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<DataPasien> getDataPasien() {
        return dataPasien;
    }

    public void setDataPasien(List<DataPasien> dataPasien) {
        this.dataPasien = dataPasien;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

