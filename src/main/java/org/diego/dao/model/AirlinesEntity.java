package org.diego.dao.model;

import java.io.Serializable;

public class AirlinesEntity implements Serializable {
    
    private static final long serialVersionUID = -787815255472810873L;
    
    private String code;
    
    private String name;
    
    private double infantPrice;

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

    public double getInfantPrice() {
        return infantPrice;
    }

    public void setInfantPrice(double infantPrice) {
        this.infantPrice = infantPrice;
    }
    
}
