package com.rsh.android.rekammedisbosimrsh.models.modelinputrm;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rezar on 01/05/2017.
 */

public class ModelInputRmSingle {

    private InputRm inputRm;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public InputRm getInputRm() {
        return inputRm;
    }

    public void setInputRm(InputRm inputRm) {
        this.inputRm = inputRm;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
