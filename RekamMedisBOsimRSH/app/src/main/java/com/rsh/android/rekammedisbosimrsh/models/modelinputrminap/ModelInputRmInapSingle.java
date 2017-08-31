package com.rsh.android.rekammedisbosimrsh.models.modelinputrminap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rezar on 02/05/2017.
 */

public class ModelInputRmInapSingle {
    private InputRmInap inputRmInap;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public InputRmInap getInputRmInap() {
        return inputRmInap;
    }

    public void setInputRmInap(InputRmInap inputRmInap) {
        this.inputRmInap = inputRmInap;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
