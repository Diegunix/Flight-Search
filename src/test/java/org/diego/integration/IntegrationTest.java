package org.diego.integration;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Locale;

import org.diego.dto.FlightSearchRequest;
import org.diego.dto.FlightSearchResponse;
import org.diego.exception.FlightSearchException;
import org.diego.service.FlightSearchService;
import org.diego.service.FlightSearchServiceImpl;
import org.junit.Before;
import org.junit.Test;

public class IntegrationTest {
    
    private FlightSearchService service;
    
    @Before
    public void setData() {
        Locale.setDefault(Locale.ENGLISH);
        service = new FlightSearchServiceImpl();
    }
    
    @Test
    public void testAssertTrueExample1() throws FlightSearchException {
        FlightSearchRequest flightRequest = new FlightSearchRequest("AMS", "FRA", 30, 1, 0, 0);
        List<FlightSearchResponse> response = service.findFlights(flightRequest);
        assertEquals(3, response.size());
        assertThat(response, hasItems(new FlightSearchResponse("TK2372", 157.6)));
        assertThat(response, hasItems(new FlightSearchResponse("TK2659", 198.4)));
        assertThat(response, hasItems(new FlightSearchResponse("LH5909", 90.4)));
    }
    
    @Test
    public void testAssertTrueExample2() throws FlightSearchException {
        FlightSearchRequest flightRequest = new FlightSearchRequest("LHR", "IST", 15, 2, 1, 1);
        List<FlightSearchResponse> response = service.findFlights(flightRequest);
        assertEquals(2, response.size());
        assertThat(response, hasItems(new FlightSearchResponse("TK8891", 806.0)));
        assertThat(response, hasItems(new FlightSearchResponse("LH1085", 481.19)));
    }
    
    @Test
    public void testAssertTrueExample3() throws FlightSearchException {
        FlightSearchRequest flightRequest = new FlightSearchRequest("BCN", "MAD", 2, 1, 2, 0);
        List<FlightSearchResponse> response = service.findFlights(flightRequest);
        assertEquals(2, response.size());
        assertThat(response, hasItems(new FlightSearchResponse("IB2171", 909.09)));
        assertThat(response, hasItems(new FlightSearchResponse("LH5496", 1028.43)));
    }
    
    @Test
    public void testAssertFalseExample4() throws FlightSearchException {
        FlightSearchRequest flightRequest = new FlightSearchRequest("CDG", "FRA", 2, 1, 0, 0);
        List<FlightSearchResponse> response = service.findFlights(flightRequest);
        assertEquals(0, response.size());
    }
    
    @Test
    public void testAssertFalseExample5() {
        FlightSearchRequest flightRequest = new FlightSearchRequest("ROD", "FRA", 2, 1, 0, 0);
        List<FlightSearchResponse> response;
        try {
            response = service.findFlights(flightRequest);
        } catch (FlightSearchException e) {
            assertThat(e.getErrorCode(), is(404));
        }
    }
    
    @Test
    public void testAssertFalseExample6() {
        FlightSearchRequest flightRequest = new FlightSearchRequest("FRA", "WAN", 2, 1, 0, 0);
        List<FlightSearchResponse> response;
        try {
            response = service.findFlights(flightRequest);
        } catch (FlightSearchException e) {
            assertThat(e.getErrorCode(), is(404));
        }
    }
    
    @Test
    public void testAssertFalseExample7() {
        FlightSearchRequest flightRequest = new FlightSearchRequest("MAD", "FRA", 2, 0, 0, 0);
        List<FlightSearchResponse> response;
        try {
            response = service.findFlights(flightRequest);
        } catch (FlightSearchException e) {
            assertThat(e.getErrorCode(), is(400));
        }
    }
    
    @Test
    public void testAssertFalseExample8() {
        FlightSearchRequest flightRequest = new FlightSearchRequest("MAD", "FRA", 0, 1, 0, 0);
        List<FlightSearchResponse> response;
        try {
            response = service.findFlights(flightRequest);
        } catch (FlightSearchException e) {
            assertThat(e.getErrorCode(), is(400));
        }
    }
}