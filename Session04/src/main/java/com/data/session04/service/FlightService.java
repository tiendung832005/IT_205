package com.data.session04.service;


import com.data.session04.entity.Flight;
import com.data.session04.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> getFlights(String departure, String destination) {
        if (departure != null && destination != null) {
            return flightRepository.findByDepartureAndDestination(departure, destination);
        }
        return flightRepository.findAll();
    }
}
