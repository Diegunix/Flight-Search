package org.diego.validations;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import org.diego.exception.FlightSearchException;
import org.junit.Test;

public class ValidationTest {
    
    @Test
    public void testAssertFalseValidationEmptyList() {
        try {
            CommonValidations.validateEmptyList(new ArrayList<>(), 404, "Empty List");
        } catch (FlightSearchException e) {
            assertThat(e.getErrorCode(), is(404));
            assertThat(e.getCauseMessage(), is("Empty List"));
        }
    }
}