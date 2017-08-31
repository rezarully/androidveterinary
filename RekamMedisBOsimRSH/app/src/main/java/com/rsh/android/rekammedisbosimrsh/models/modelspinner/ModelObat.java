package com.rsh.android.rekammedisbosimrsh.models.modelspinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rezar on 26/05/2017.
 */

public class ModelObat {
    private List<Obat> obat = new ArrayList<Obat>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<Obat> getObat() {
        return obat;
    }

    public void setObat(List<Obat> obat) {
        this.obat = obat;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
