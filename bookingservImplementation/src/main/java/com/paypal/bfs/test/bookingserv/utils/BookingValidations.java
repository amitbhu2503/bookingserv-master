package com.paypal.bfs.test.bookingserv.utils;

import com.paypal.bfs.test.bookingserv.api.model.Address;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.exceptions.BookingErrorEnum;
import com.paypal.bfs.test.bookingserv.exceptions.BookingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

@Component
@Slf4j
public class BookingValidations {

    public static void validateBookingRequest(Booking booking){
        log.info("Validating request " + booking);
        // Validate booking params
        if(StringUtils.isEmpty(booking.getFirstName())){
            throw new BookingException(HttpStatus.BAD_REQUEST.value(), BookingErrorEnum.FIRST_NAME_EMPTY.getMessage(), BookingErrorEnum.FIRST_NAME_EMPTY.name());
        }
        if(StringUtils.isEmpty(booking.getLastName())){
            throw new BookingException(HttpStatus.BAD_REQUEST.value(), BookingErrorEnum.LAST_NAME_EMPTY.getMessage(), BookingErrorEnum.LAST_NAME_EMPTY.name());
        }

        try{
            Double totalPrice = Double.valueOf(booking.getTotalPrice());
        }catch (Exception ex){
            throw new BookingException(HttpStatus.BAD_REQUEST.value(), BookingErrorEnum.INVALID_TOTAL_PRICE.getMessage(), BookingErrorEnum.INVALID_TOTAL_PRICE.name());
        }

        try{
            Double deposit = Double.valueOf(booking.getDeposit());
        }catch (Exception ex){
            throw new BookingException(HttpStatus.BAD_REQUEST.value(), BookingErrorEnum.INVALID_DEPOSIT.getMessage(), BookingErrorEnum.INVALID_DEPOSIT.name());
        }

        Date dob = DateUtil.getDOBDate(booking.getDateOfBirth());
        Date checkin = DateUtil.getCheckinCheckoutDate(booking.getCheckIn());
        Date checkout = DateUtil.getCheckinCheckoutDate(booking.getCheckOut());

        // validate address

        if(booking.getAddress() == null){
            throw new BookingException(HttpStatus.BAD_REQUEST.value(), BookingErrorEnum.INVALID_ADDRESS.getMessage(), BookingErrorEnum.INVALID_ADDRESS.name());
        }

        Address address = booking.getAddress();

        if(StringUtils.isEmpty(address.getLine1())){
            throw new BookingException(HttpStatus.BAD_REQUEST.value(), BookingErrorEnum.INVALID_ADDRESS_LINE1.getMessage(), BookingErrorEnum.INVALID_ADDRESS_LINE1.name());
        }

        if(StringUtils.isEmpty(address.getState())){
            throw new BookingException(HttpStatus.BAD_REQUEST.value(), BookingErrorEnum.INVALID_ADDRESS_STATE.getMessage(), BookingErrorEnum.INVALID_ADDRESS_STATE.name());
        }

        if(StringUtils.isEmpty(address.getCity())){
            throw new BookingException(HttpStatus.BAD_REQUEST.value(), BookingErrorEnum.INVALID_ADDRESS_CITY.getMessage(), BookingErrorEnum.INVALID_ADDRESS_CITY.name());
        }

        if(StringUtils.isEmpty(address.getCountry())){
            throw new BookingException(HttpStatus.BAD_REQUEST.value(), BookingErrorEnum.INVALID_ADDRESS_COUNTRY.getMessage(), BookingErrorEnum.INVALID_ADDRESS_COUNTRY.name());
        }

        if(StringUtils.isEmpty(address.getZipCode())){
            throw new BookingException(HttpStatus.BAD_REQUEST.value(), BookingErrorEnum.INVALID_ADDRESS_ZIPCODE.getMessage(), BookingErrorEnum.INVALID_ADDRESS_ZIPCODE.name());
        }
    }
}
