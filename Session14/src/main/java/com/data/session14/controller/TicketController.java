package com.data.session14.controller;

import com.data.session14.model.entity.Ticket;
import com.data.session14.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @PostMapping("/book")
    public ResponseEntity<Ticket> bookTicket(@RequestParam Long showtimeId,
                                             @RequestParam String seatNumber,
                                             @RequestParam BigDecimal price,
                                             Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(ticketService.bookTicket(showtimeId, seatNumber, price, username));
    }

    @GetMapping("/my")
    public ResponseEntity<List<Ticket>> getMyTickets(Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(ticketService.getTicketsByUser(username));
    }

    @GetMapping("/admin")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }
}