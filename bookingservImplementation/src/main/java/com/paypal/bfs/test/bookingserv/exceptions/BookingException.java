package com.paypal.bfs.test.bookingserv.exceptions;

public class BookingException extends RuntimeException {

    private int status;
    private String errorMessage;
    private String errorCode;

    public BookingException(int status, String errorMessage, String errorCode){
        this.status = status;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public BookingException(int status, String errorMessage){
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
