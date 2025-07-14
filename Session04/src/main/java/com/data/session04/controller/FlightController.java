package com.data.session04.controller;

package com.arline.booking.controller;

import com.data.session04.entity.Booking;
import com.data.session04.entity.Flight;
import com.data.session04.service.BookingService;
import com.data.session04.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public String getFlights(@RequestParam(required = false) String departure,
                             @RequestParam(required = false) String destination,
                             Model model) {
        List<Flight> flights = flightService.getFlights(departure, destination);
        model.addAttribute("flights", flights);
        return "flights";
    }

    @PostMapping("/book")
    public String bookFlight(@RequestParam Integer flightId,
                             @RequestParam String customerName,
                             @RequestParam String customerPhone) {
        bookingService.bookFlight(flightId, customerName, customerPhone);
        return "redirect:/flights";
    }

    @GetMapping("/bookings")
    public String getBookings(@RequestParam String customerName, Model model) {
        List<Booking> bookings = bookingService.getBookingsByCustomer(customerName);
        model.addAttribute("bookings", bookings);
        return "bookings";
    }

    @GetMapping("/cancel")
    public String cancelBooking(@RequestParam Integer bookingId) {
        bookingService.cancelBooking(bookingId);
        return "redirect:/flights/bookings";
    }
}
