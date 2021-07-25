package com.paypal.bfs.test.bookingserv.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@ControllerAdvice
public class ControllerErrorHandler {
    @ExceptionHandler(BookingException.class)
    void handleAPException(BookingException e, HttpServletResponse response) throws IOException {
        response.sendError(e.getStatus(), e.getErrorMessage());
    }
}
