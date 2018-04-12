package org.diego.dao.model;

import java.io.Serializable;

public class FlightsEntity implements Serializable {
    
    private static final long serialVersionUID = -787815255472810873L;
    
    private String originCode;
    
    private String destinationCode;
    
    private String airlineCode;
    
    private double basePrice;
    
    public String getOriginCode() {
        return originCode;
    }
    
    public void setOriginCode(String originCode) {
        this.originCode = originCode;
    }
    
    public String getDestinationCode() {
        return destinationCode;
    }
    
    public void setDestinationCode(String destinationCode) {
        this.destinationCode = destinationCode;
    }
    
    public String getAirlineCode() {
        return airlineCode;
    }
    
    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }
    
    public double getBasePrice() {
        return basePrice;
    }
    
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }
    
}
