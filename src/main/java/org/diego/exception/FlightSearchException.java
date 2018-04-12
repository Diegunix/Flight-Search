package org.diego.exception;

public class FlightSearchException extends Exception {
    
    private static final long serialVersionUID = -9054419021494718464L;
    
    private final Integer errorCode;
    
    private final String causeMessage;
    
    public FlightSearchException(int error, String causeMessage) {
        this.errorCode = error;
        this.causeMessage = causeMessage;
    }
    
    public Integer getErrorCode() {
        return errorCode;
    }

    public String getCauseMessage() {
        return causeMessage;
    }

    public String getMessageError() {
        return "Error:" + errorCode + " Cause: " + causeMessage;
    }
    
}
