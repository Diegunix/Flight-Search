package org.diego.validations;

import java.util.List;

import org.diego.exception.FlightSearchException;

public class CommonValidations {
    
    private CommonValidations() {
        throw new IllegalStateException("Utility class");
    }
    
    public static void validateNullPointer(Object data, int error, String code) throws FlightSearchException {
        if (data == null) {
            throw new FlightSearchException(error, code);
        }
    }
    
    public static void validateEmptyList(List<?> data, int error, String code) throws FlightSearchException {
        if (data == null || data.isEmpty()) {
            throw new FlightSearchException(error, code);
        }
    }
}
