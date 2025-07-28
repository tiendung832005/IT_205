package com.data.session15.service;

import com.data.session15.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final OrderRepository orderRepository;

    public BigDecimal getRevenue(String type) {
        LocalDate startDate;
        LocalDate endDate = LocalDate.now();

        switch (type.toLowerCase()) {
            case "day":
                startDate = endDate;
                break;
            case "month":
                startDate = endDate.withDayOfMonth(1);
                break;
            case "year":
                startDate = endDate.withDayOfYear(1);
                break;
            default:
                throw new IllegalArgumentException("Invalid report type");
        }

        return orderRepository.findAll().stream()
                .filter(order -> !order.getCreatedDate().toLocalDate().isBefore(startDate) &&
                        !order.getCreatedDate().toLocalDate().isAfter(endDate))
                .map(order -> order.getTotalMoney())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}