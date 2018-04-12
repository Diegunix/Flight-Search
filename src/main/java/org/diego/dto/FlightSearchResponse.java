package org.diego.dto;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Objects;

public class FlightSearchResponse {
    
    private String flightCode;
    
    private double price;
    
    private static final NumberFormat PRICE_FORMAT = new DecimalFormat("#0.00");
    
    public FlightSearchResponse(String flightCode, Double price) {
        this.flightCode = flightCode;
        this.price = price;
    }
    
    public String getFlightCode() {
        return flightCode;
    }
    
    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }
    
    public double getPrice() {
        return Double.valueOf(PRICE_FORMAT.format(price));
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        FlightSearchResponse flight = (FlightSearchResponse) o;
        return getPrice() == flight.getPrice() && Objects.equals(flightCode, flight.flightCode);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(flightCode, getPrice());
    }
}