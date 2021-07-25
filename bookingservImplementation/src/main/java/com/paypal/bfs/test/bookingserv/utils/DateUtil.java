package com.paypal.bfs.test.bookingserv.utils;

import com.paypal.bfs.test.bookingserv.exceptions.BookingException;
import org.springframework.http.HttpStatus;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final String DATE_OF_BIRTH_DATE_FORMAT = "yyyy-MM-dd";
    private static final String CHECKIN_CHECKOUT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";


    public static String getDOBString(Date date) {
        if (date == null)
            return "";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_OF_BIRTH_DATE_FORMAT);
        return sdf.format(date);
    }

    public static Date getDOBDate(String timestamp) {
        DateFormat format = new SimpleDateFormat(DATE_OF_BIRTH_DATE_FORMAT);
        format.setLenient(false);
        try {
            return format.parse(timestamp);
        } catch (Exception e) {
            throw new BookingException(HttpStatus.BAD_REQUEST.value(), "Please send date_of_birth in " + DATE_OF_BIRTH_DATE_FORMAT + " format");
        }
    }

    public static String getCheckinCheckoutString(Date date) {
        if (date == null)
            return "";
        SimpleDateFormat sdf = new SimpleDateFormat(CHECKIN_CHECKOUT_DATE_FORMAT);
        return sdf.format(date);
    }

    public static Date getCheckinCheckoutDate(String timestamp) {
        DateFormat format = new SimpleDateFormat(CHECKIN_CHECKOUT_DATE_FORMAT);
        format.setLenient(false);
        try {
            return format.parse(timestamp);
        } catch (Exception e) {
            throw new BookingException(HttpStatus.BAD_REQUEST.value(), "Please check_in or check_out date in " + CHECKIN_CHECKOUT_DATE_FORMAT + " format");
        }
    }

}
