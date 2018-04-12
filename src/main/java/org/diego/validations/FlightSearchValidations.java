package org.diego.validations;

import org.diego.dto.FlightSearchRequest;
import org.diego.exception.FlightSearchException;

public class FlightSearchValidations {
    
    private FlightSearchValidations() {
        throw new IllegalStateException("Utility class");
    }
    
    /**
     * @param flightRequest
     * @throws FlightSearchException
     */
    public static void validateInput(FlightSearchRequest flightRequest) throws FlightSearchException {
        if (flightRequest.getDepartureFrom() < 1 || flightRequest.getDepartureFrom() > 365) {
            throw new FlightSearchException(400, "The departure is invalid");
        }
        if (flightRequest.getAdultAmount() == 0 && flightRequest.getChildAmount() == 0
                && flightRequest.getInfantAmount() == 0) {
            throw new FlightSearchException(400, "The data is invalid");
        }
        
    }
}
