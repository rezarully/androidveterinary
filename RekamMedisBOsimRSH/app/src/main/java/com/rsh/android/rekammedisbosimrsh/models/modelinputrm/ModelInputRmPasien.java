package com.rsh.android.rekammedisbosimrsh.models.modelinputrm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rezar on 29/04/2017.
 */

public class ModelInputRmPasien {
    private List<InputRm> inputRm = new ArrayList<InputRm>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<InputRm> getInputRm() {
        return inputRm;
    }

    public void setInputRm(List<InputRm> inputRm) {
        this.inputRm = inputRm;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}

