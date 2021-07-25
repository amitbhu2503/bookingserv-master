package com.paypal.bfs.test.bookingserv.exceptions;

public enum  BookingErrorEnum {
    FIRST_NAME_EMPTY("Empty first_name in request"),
    LAST_NAME_EMPTY("Empty last_name in request"),
    INVALID_TOTAL_PRICE("Invalid total_price"),
    INVALID_DEPOSIT("Invalid deposit"),
    INVALID_ADDRESS("Invalid Address"),
    INVALID_ADDRESS_LINE1("Invalid Address line 1"),
    INVALID_ADDRESS_CITY("Invalid Address city"),
    INVALID_ADDRESS_STATE("Invalid Address state"),
    INVALID_ADDRESS_COUNTRY("Invalid Address country"),
    INVALID_ADDRESS_ZIPCODE("Invalid Address zipcode"),
    DUPLICATE_BOOKING_REQUEST("Duplicate Booking Request");

    private String message;

    BookingErrorEnum(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
