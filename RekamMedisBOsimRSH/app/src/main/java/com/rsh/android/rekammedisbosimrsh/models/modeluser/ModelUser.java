package com.rsh.android.rekammedisbosimrsh.models.modeluser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rezar on 17/05/2017.
 */

public class ModelUser {
    private List<User> user = new ArrayList<User>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
