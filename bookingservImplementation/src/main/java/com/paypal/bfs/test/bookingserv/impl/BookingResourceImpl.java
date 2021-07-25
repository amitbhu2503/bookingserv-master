package com.paypal.bfs.test.bookingserv.impl;

import com.paypal.bfs.test.bookingserv.api.BookingResource;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.entity.BookingEntity;
import com.paypal.bfs.test.bookingserv.exceptions.BookingErrorEnum;
import com.paypal.bfs.test.bookingserv.exceptions.BookingException;
import com.paypal.bfs.test.bookingserv.repository.BookingDao;
import com.paypal.bfs.test.bookingserv.repository.BookingRepository;
import com.paypal.bfs.test.bookingserv.utils.BookingValidations;
import com.paypal.bfs.test.bookingserv.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class BookingResourceImpl implements BookingResource {

    @Autowired
    BookingRepository bookingRepository;

    @Override
    public ResponseEntity<Booking> create(Booking booking) {
        log.info("Processing booking resource request " + booking);
        // Validate Booking
        BookingValidations.validateBookingRequest(booking);
        // check for duplicate creation
        List<BookingEntity> existingBookings = bookingRepository.findByFirstNameAndLastNameAndDateOfBirthAndCheckOutAndCheckInTime(
                booking.getFirstName(),
                booking.getLastName(),
                DateUtil.getDOBDate(booking.getDateOfBirth()),
                DateUtil.getCheckinCheckoutDate(booking.getCheckOut()),
                DateUtil.getCheckinCheckoutDate(booking.getCheckIn())
        );

        if(!CollectionUtils.isEmpty(existingBookings)){
            throw new BookingException(HttpStatus.CONFLICT.value(), BookingErrorEnum.DUPLICATE_BOOKING_REQUEST.getMessage(), BookingErrorEnum.DUPLICATE_BOOKING_REQUEST.name());
        }

        // procees request
        BookingEntity bookingEntity = bookingRepository.save(BookingDao.getBookingEntity(booking));
        // return response
        Booking result = BookingDao.getBooking(bookingEntity);
        return new ResponseEntity<Booking>(result, HttpStatus.CREATED);
    }

    @Override
    public List<Booking> getAll() {
        log.info("Processing get all booking request");
        Iterable<BookingEntity> bookingEntities = bookingRepository.findAll();
        List<Booking> results = new ArrayList<Booking>();
        bookingEntities.forEach((bookingEntitie)->{
                results.add(BookingDao.getBooking(bookingEntitie));
        });
        return results;
    }
}
