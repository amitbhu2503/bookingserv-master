package com.paypal.bfs.test.bookingserv.repository;

import com.paypal.bfs.test.bookingserv.api.model.Address;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.entity.BookingEntity;
import com.paypal.bfs.test.bookingserv.utils.DateUtil;

public class BookingDao {

    public static Booking getBooking(BookingEntity bookingEntity){
        if(bookingEntity != null){
            Booking booking =  new Booking();
            booking.setId(bookingEntity.getId());
            booking.setFirstName(bookingEntity.getFirstName());
            booking.setLastName(bookingEntity.getLastName());
            booking.setDateOfBirth(DateUtil.getDOBString(bookingEntity.getDateOfBirth()));
            booking.setCheckIn(DateUtil.getCheckinCheckoutString(bookingEntity.getCheckInTime()));
            booking.setCheckOut(DateUtil.getCheckinCheckoutString(bookingEntity.getCheckOut()));
            booking.setTotalPrice(String.valueOf(bookingEntity.getTotalPrice()));
            booking.setDeposit(String.valueOf(bookingEntity.getDeposit()));
            booking.setAddress(AddressDao.getAddress(bookingEntity.getAddress()));
            return booking;
        }
        return null;
    }


    public static BookingEntity getBookingEntity(Booking booking){
        if(booking != null){
            BookingEntity bookingEntity = new BookingEntity();
            bookingEntity.setId(booking.getId());
            bookingEntity.setFirstName(booking.getFirstName());
            bookingEntity.setLastName(booking.getLastName());
            bookingEntity.setDateOfBirth(DateUtil.getDOBDate(booking.getDateOfBirth()));
            bookingEntity.setCheckInTime(DateUtil.getCheckinCheckoutDate(booking.getCheckIn()));
            bookingEntity.setCheckOut(DateUtil.getCheckinCheckoutDate(booking.getCheckOut()));
            bookingEntity.setTotalPrice(Double.valueOf(booking.getTotalPrice()));
            bookingEntity.setDeposit(Double.valueOf(booking.getDeposit()));
            bookingEntity.setAddress(AddressDao.getAddressEntity(booking.getAddress()));
            return bookingEntity;
        }
        return null;
    }
}
