package com.rsh.android.rekammedisbosimrsh.models.modelspinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rezar on 26/05/2017.
 */

public class ModelTindakan {
    private List<Tindakan> tindakan = new ArrayList<Tindakan>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<Tindakan> getTindakan() {
        return tindakan;
    }

    public void setTindakan(List<Tindakan> tindakan) {
        this.tindakan = tindakan;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
