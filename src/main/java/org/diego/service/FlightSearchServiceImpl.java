package org.diego.service;

import java.util.ArrayList;
import java.util.List;

import org.diego.dao.db.AirlinesRepository;
import org.diego.dao.db.AirportRepository;
import org.diego.dao.db.FlightsRepository;
import org.diego.dao.db.PricingRulesRepository;
import org.diego.dao.db.csv.AirlinesRepositoryImpl;
import org.diego.dao.db.csv.AirportRepositoryImpl;
import org.diego.dao.db.csv.FlightsRepositoryImpl;
import org.diego.dao.db.csv.PricingRulesRepositoryImpl;
import org.diego.dao.model.AirlinesEntity;
import org.diego.dao.model.AirportEntity;
import org.diego.dao.model.FlightsEntity;
import org.diego.dao.model.PricingRulesEntity;
import org.diego.dto.FlightSearchRequest;
import org.diego.dto.FlightSearchResponse;
import org.diego.exception.FlightSearchException;
import org.diego.validations.CommonValidations;
import org.diego.validations.FlightSearchValidations;

public class FlightSearchServiceImpl implements FlightSearchService {
    
    private static final double CHILD_DISCOUNT = 0.67;
    
    AirlinesRepository airlines = new AirlinesRepositoryImpl();
    AirportRepository airport = new AirportRepositoryImpl();
    FlightsRepository flights = new FlightsRepositoryImpl();
    PricingRulesRepository pricing = new PricingRulesRepositoryImpl();
    
    @Override
    public List<FlightSearchResponse> findFlights(FlightSearchRequest flightRequest) throws FlightSearchException {
        
        FlightSearchValidations.validateInput(flightRequest);
        AirportEntity origin = airport.getByCode(flightRequest.getAirportOriginCode());
        CommonValidations.validateNullPointer(origin, 404, "The Airport origin not exists");
        
        AirportEntity destination = airport.getByCode(flightRequest.getAirportDestinationCode());
        CommonValidations.validateNullPointer(destination, 404, "The Airport destination not exists");
        
        List<FlightsEntity> flightList = flights.getByFlight(origin.getCode(), destination.getCode());
        
        return setPriceBase(flightList, flightRequest.getDepartureFrom(), flightRequest.getChildAmount(),
                flightRequest.getInfantAmount(), flightRequest.getAdultAmount());
    }
    
    private List<FlightSearchResponse> setPriceBase(List<FlightsEntity> flightList, int departureFrom, int childs,
            int infants, int adult) {
        List<FlightSearchResponse> res = new ArrayList<>();
        double priceFinal;
        for (FlightsEntity flight : flightList) {
            AirlinesEntity airline = airlines.getByIATA(flight.getAirlineCode());
            PricingRulesEntity pricingRule = pricing.getByDepartoure(departureFrom);
            double percentPrice = (double) pricingRule.getPercentBase() / 100;
            priceFinal = airline.getInfantPrice() * infants;
            priceFinal += ((flight.getBasePrice() * percentPrice) * CHILD_DISCOUNT) * childs;
            priceFinal += (flight.getBasePrice() * percentPrice) * adult;
            FlightSearchResponse flightResponse = new FlightSearchResponse(flight.getAirlineCode(), priceFinal);
            res.add(flightResponse);
        }
        return res;
    }
}
