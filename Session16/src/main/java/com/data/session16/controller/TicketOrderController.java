package com.data.session16.controller;

import com.data.session16.model.entity.Combo;
import com.data.session16.model.entity.TicketOrder;
import com.data.session16.model.entity.User;
import com.data.session16.service.TicketOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/ticket-orders")
@RequiredArgsConstructor
public class TicketOrderController {
    private final TicketOrderService ticketOrderService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TicketOrder> placeOrder(
            @RequestParam Integer quantityTicket,
            @RequestBody List<Combo> combos,
            Principal principal) {
        User user = getUserFromPrincipal(principal); // Implement this method to fetch the user
        return ResponseEntity.ok(ticketOrderService.placeOrder(user, quantityTicket, combos));
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<TicketOrder>> getMyOrders(Principal principal) {
        User user = getUserFromPrincipal(principal); // Implement this method to fetch the user
        return ResponseEntity.ok(ticketOrderService.getUserOrders(user));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<List<TicketOrder>> getAllOrders() {
        return ResponseEntity.ok(ticketOrderService.getAllOrders());
    }

    private User getUserFromPrincipal(Principal principal) {
        // Implement logic to fetch User entity from the principal (e.g., using UserService)
        return null;
    }
}