package com.paypal.bfs.test.bookingserv;

import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import com.paypal.bfs.test.bookingserv.api.model.Booking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BookingServApplicationTest {

    @Autowired
    private MockMvc mvc;

    private String bookingJson;
    private String booking1Json;
    private String booking2Json;

    @Before
    public void setUp() throws IOException {
        InputStream is = BookingServApplicationTest.class.getClassLoader().getResourceAsStream("booking.json");
        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader
                (is, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        }
        bookingJson = textBuilder.toString();

        InputStream is1 = BookingServApplicationTest.class.getClassLoader().getResourceAsStream("booking1.json");
        StringBuilder textBuilder1 = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader
                (is1, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder1.append((char) c);
            }
        }
        booking1Json = textBuilder1.toString();

        InputStream is2 = BookingServApplicationTest.class.getClassLoader().getResourceAsStream("booking2.json");
        StringBuilder textBuilder2 = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader
                (is2, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder2.append((char) c);
            }
        }
        booking2Json = textBuilder2.toString();
    }

    @Test
    public void createBooking_thenStatus201()
            throws Exception {
        log.info("Executing test 1 createBooking_thenStatus201");
        this.mvc.perform(post("/v1/bfs/booking")
                .contentType(MediaType.APPLICATION_JSON).content(bookingJson))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        log.info("Finished test 1 createBooking_thenStatus201");

    }

    @Test
    public void createBooking_thenStatus400()
            throws Exception {
        log.info("Executing test 2 createBooking_thenStatus400");
        this.mvc.perform(post("/v1/bfs/booking")
                .contentType(MediaType.APPLICATION_JSON).content(booking2Json))
                .andExpect(status().isBadRequest());
        log.info("Finished test 2 createBooking_thenStatus400");

    }

    @Test
    public void getbooking_thenStatus200()  throws Exception {
        log.info("Executing test 3 getbooking_thenStatus200");
        mvc.perform(get("/v1/bfs/bookings")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        log.info("Finished test 3 getbooking_thenStatus200");
    }

    @Test
    public void createduplicatebooking_thenStatus409()
            throws Exception {
        log.info("Executing test 4 createduplicatebooking_thenStatus409");
        this.mvc.perform(post("/v1/bfs/booking")
                .contentType(MediaType.APPLICATION_JSON).content(booking1Json));

        this.mvc.perform(post("/v1/bfs/booking")
                .contentType(MediaType.APPLICATION_JSON).content(booking1Json))
                .andExpect(status().isConflict());
        log.info("Finished test 4 createduplicatebooking_thenStatus409");
    }
}
