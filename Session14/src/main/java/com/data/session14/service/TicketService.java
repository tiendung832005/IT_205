package com.data.session14.service;

import com.data.session14.model.entity.Showtime;
import com.data.session14.model.entity.Ticket;
import com.data.session14.model.entity.User;
import com.data.session14.repository.ShowtimeRepository;
import com.data.session14.repository.TicketRepository;
import com.data.session14.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final ShowtimeRepository showtimeRepository;

    public Ticket bookTicket(Long showtimeId, String seatNumber, BigDecimal price, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Showtime showtime = showtimeRepository.findById(showtimeId)
                .orElseThrow(() -> new RuntimeException("Showtime not found"));

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setShowtime(showtime);
        ticket.setSeatNumber(seatNumber);
        ticket.setBookingTime(LocalDateTime.now());
        ticket.setPrice(price);

        return ticketRepository.save(ticket);
    }

    public List<Ticket> getTicketsByUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ticketRepository.findByUser(user);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }
}