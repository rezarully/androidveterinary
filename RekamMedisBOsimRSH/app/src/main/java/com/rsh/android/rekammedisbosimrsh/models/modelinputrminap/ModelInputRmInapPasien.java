package com.rsh.android.rekammedisbosimrsh.models.modelinputrminap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rezar on 02/05/2017.
 */

public class ModelInputRmInapPasien {
    private List<InputRmInap> inputRmInap = new ArrayList<InputRmInap>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<InputRmInap> getInputRmInap() {
        return inputRmInap;
    }

    public void setInputRmInap(List<InputRmInap> inputRmInap) {
        this.inputRmInap = inputRmInap;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
