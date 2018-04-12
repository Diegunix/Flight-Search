package org.diego.dao.model;

import java.io.Serializable;

public class AirportEntity implements Serializable {
    
    private static final long serialVersionUID = -787815255472810873L;
    
    private String code;
    
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
