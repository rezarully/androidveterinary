package com.rsh.android.rekammedisbosimrsh.models.modeluser;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rezar on 17/05/2017.
 */

public class User {
    private String id;
    private String username;
    private String password;
    private String level;
    private String nama;
    private String entri;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEntri() {
        return entri;
    }

    public void setEntri(String entri) {
        this.entri = entri;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
