package com.paypal.bfs.test.bookingserv.repository;

import com.paypal.bfs.test.bookingserv.entity.BookingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.paypal.bfs.test.bookingserv.api.model.Booking;

import java.util.Date;
import java.util.List;

@Repository
public interface BookingRepository extends CrudRepository<BookingEntity, Integer> {

    List<BookingEntity> findByFirstNameAndLastNameAndDateOfBirthAndCheckOutAndCheckInTime(@Param(value = "firstName") String firstName,
                                                                           @Param(value = "lastName") String lastName,
                                                                           @Param(value = "dateOfBirth") Date dateOfBirth,
                                                                           @Param(value = "checkOut") Date checkOut,
                                                                           @Param(value = "checkInTime") Date checkInTime);
}
