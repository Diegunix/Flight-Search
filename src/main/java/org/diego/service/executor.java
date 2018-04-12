package org.diego.service;

import java.util.List;
import java.util.Locale;

import org.diego.dto.FlightSearchRequest;
import org.diego.dto.FlightSearchResponse;

public class executor {

    
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.ENGLISH);
        FlightSearchServiceImpl service = new FlightSearchServiceImpl();
        FlightSearchRequest flightRequest = new FlightSearchRequest("AMS", "FRA", 30, 1, 0, 0);
        List<FlightSearchResponse> res = service.findFlights(flightRequest);
        for (FlightSearchResponse f : res) {
            System.out.println(f.getFlightCode() + " | " + f.getPrice());
        }
    }
    
}

