package com.paypal.bfs.test.bookingserv.controllers;


import com.paypal.bfs.test.bookingserv.api.BookingResource;
import com.paypal.bfs.test.bookingserv.exceptions.BookingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.paypal.bfs.test.bookingserv.api.model.Booking;

import java.util.List;


@RestController
@Slf4j
public class BookingController {

    @Autowired
    BookingResource bookingResource;

    @RequestMapping(value = "/v1/bfs/booking", method = RequestMethod.POST)
    public ResponseEntity<Booking> create(@RequestBody Booking booking) {
        log.info("received request to create booking " + booking);
        try {
            return bookingResource.create(booking);
        }catch (BookingException e){
            log.error("Error in processing request " + booking);
            throw e;
        }
    }

    @RequestMapping(value = "/v1/bfs/bookings", method = RequestMethod.GET)
    public List<Booking> getAll() {
        log.info("received request to get all bookings ");
        return bookingResource.getAll();
    }
}
