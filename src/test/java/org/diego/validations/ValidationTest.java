package org.diego.validations;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import org.diego.dto.FlightSearchRequest;
import org.diego.exception.FlightSearchException;
import org.junit.Test;

public class ValidationTest {
    
    FlightSearchRequest flightReques = new FlightSearchRequest(null, null, 0, 0, 0, 0);
    
    @Test
    public void testAssertFalseValidationEmptyList() {
        try {
            CommonValidations.validateEmptyList(new ArrayList<>(), 404, "Empty List");
        } catch (FlightSearchException e) {
            assertThat(e.getErrorCode(), is(404));
            assertThat(e.getCauseMessage(), is("Empty List"));
        }
    }
    
    @Test
    public void testAssertFalseValidationNullList() {
        try {
            CommonValidations.validateEmptyList(null, 404, "Null List");
        } catch (FlightSearchException e) {
            assertThat(e.getErrorCode(), is(404));
            assertThat(e.getCauseMessage(), is("Null List"));
        }
    }
    
    @Test
    public void testAssertFalseValidationNullPointer() {
        try {
            CommonValidations.validateNullPointer(null, 404, "Null Pointer");
        } catch (FlightSearchException e) {
            assertThat(e.getErrorCode(), is(404));
            assertThat(e.getCauseMessage(), is("Null Pointer"));
        }
    }
    
    @Test
    public void testAssertFalseValidationFlightSearch() {
        flightReques.setDepartureFrom(0);
        try {
            FlightSearchValidations.validateInput(flightReques);
        } catch (FlightSearchException e) {
            assertThat(e.getErrorCode(), is(400));
            assertThat(e.getCauseMessage(), is("The departure is invalid"));
        }
    }
    
    @Test
    public void testAssertFalseValidationFlightSearch2() {
        flightReques.setDepartureFrom(5);
        try {
            FlightSearchValidations.validateInput(flightReques);
        } catch (FlightSearchException e) {
            assertThat(e.getErrorCode(), is(400));
            assertThat(e.getCauseMessage(), is("The data is invalid"));
        }
    }
    
}