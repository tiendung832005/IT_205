package com.data.session04.service;


import com.data.session04.entity.Booking;
import com.data.session04.entity.Flight;
import com.data.session04.repository.BookingRepository;
import com.data.session04.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FlightRepository flightRepository;

    public Booking bookFlight(Integer flightId, String customerName, String customerPhone) {
        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new RuntimeException("Flight not found"));
        Booking booking = new Booking(null, flight, customerName, customerPhone, LocalDateTime.now(), "BOOKED");
        return bookingRepository.save(booking);
    }

    public List<Booking> getBookingsByCustomer(String customerName) {
        return bookingRepository.findByCustomerName(customerName);
    }

    public void cancelBooking(Integer bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus("CANCELLED");
        bookingRepository.save(booking);
    }
}
