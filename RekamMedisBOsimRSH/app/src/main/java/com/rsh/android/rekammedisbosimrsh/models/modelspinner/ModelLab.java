package com.rsh.android.rekammedisbosimrsh.models.modelspinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rezar on 26/05/2017.
 */

public class ModelLab {
    private List<Lab> lab = new ArrayList<Lab>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<Lab> getLab() {
        return lab;
    }

    public void setLab(List<Lab> lab) {
        this.lab = lab;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
