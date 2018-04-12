package org.diego.service;

import java.util.List;

import org.diego.dto.FlightSearchRequest;
import org.diego.dto.FlightSearchResponse;
import org.diego.exception.FlightSearchException;

public interface FlightSearchService {
    
    List<FlightSearchResponse> findFlights(FlightSearchRequest flightRequest) throws FlightSearchException;
    
}
