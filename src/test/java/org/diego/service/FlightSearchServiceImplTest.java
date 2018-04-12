package org.diego.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FlightSearchServiceImplTest {
    
    @InjectMocks
    private FlightSearchServiceImpl service;
    
    @Mock
    private AirlinesRepositoryImpl airlines;
    @Mock
    private AirportRepositoryImpl airport;
    @Mock
    private FlightsRepositoryImpl flights;
    @Mock
    private PricingRulesRepositoryImpl pricing;
    
    @Before
    public void setupMock() {
        Locale.setDefault(Locale.ENGLISH);
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void findFlightsTest() throws FlightSearchException {
        
        FlightSearchResponse flightSearchResponse = new FlightSearchResponse("code", 100.0);
        
        // Mock
        FlightSearchRequest flightSearchRequest = mock(FlightSearchRequest.class);
        when(flightSearchRequest.getAdultAmount()).thenReturn(1);
        when(flightSearchRequest.getDepartureFrom()).thenReturn(30);
        when(flightSearchRequest.getChildAmount()).thenReturn(0);
        when(flightSearchRequest.getInfantAmount()).thenReturn(0);
        when(flightSearchRequest.getAirportOriginCode()).thenReturn("AMS");
        when(flightSearchRequest.getAirportDestinationCode()).thenReturn("FRA");
        
        List<FlightsEntity> listFlights = new ArrayList<FlightsEntity>();
        FlightsEntity fligh1 = new FlightsEntity();
        fligh1.setBasePrice(100.0);
        fligh1.setAirlineCode("code");
        listFlights.add(fligh1);
        
        when(airlines.getByIATA(anyString())).thenReturn(new AirlinesEntity());
        
        PricingRulesEntity price = new PricingRulesEntity();
        price.setPercentBase(100);
        when(pricing.getByDepartoure(anyInt())).thenReturn(price);
        
        when(airport.getByCode(any())).thenReturn(new AirportEntity());
        when(flights.getByFlight(any(), any())).thenReturn(listFlights);
        
        List<FlightSearchResponse> result = service.findFlights(flightSearchRequest);
        
        // Assert
        assertTrue(result.get(0).getFlightCode().equalsIgnoreCase(flightSearchResponse.getFlightCode()));
        assertTrue(result.get(0).getPrice() == flightSearchResponse.getPrice());
        
    }
}
