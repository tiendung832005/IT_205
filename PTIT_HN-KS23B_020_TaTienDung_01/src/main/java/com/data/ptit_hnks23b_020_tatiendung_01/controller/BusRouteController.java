package com.data.ptit_hnks23b_020_tatiendung_01.controller;


import com.data.ptit_hnks23b_020_tatiendung_01.dto.ApiResponse;
import com.data.ptit_hnks23b_020_tatiendung_01.entity.BusRoute;
import com.data.ptit_hnks23b_020_tatiendung_01.service.BusRouteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bus-routes")
public class BusRouteController {

    private final BusRouteService busRouteService;

    public BusRouteController(BusRouteService busRouteService) {
        this.busRouteService = busRouteService;
    }

    // Lấy danh sách
    @GetMapping
    public ResponseEntity<ApiResponse<List<BusRoute>>> getAllBusRoutes() {
        List<BusRoute> busRoutes = busRouteService.getAllBusRoutes();
        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy danh sách thành công", busRoutes, HttpStatus.OK.value()));
    }

    // Thêm
    @PostMapping
    public ResponseEntity<ApiResponse<BusRoute>> addBusRoute(@RequestBody BusRoute busRoute) {
        BusRoute newBusRoute = busRouteService.addBusRoute(busRoute);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Thêm chuyến thành công", newBusRoute, HttpStatus.CREATED.value()));
    }

    // Sửa
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BusRoute>> updateBusRoute(@PathVariable Integer id, @RequestBody BusRoute busRoute) {
        BusRoute updatedBusRoute = busRouteService.updateBusRoute(id, busRoute);
        return ResponseEntity.ok(new ApiResponse<>(true, "Cập nhật thành công", updatedBusRoute, HttpStatus.OK.value()));
    }

    // Xóa
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBusRoute(@PathVariable Integer id) {
        busRouteService.deleteBusRoute(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Xóa thành công ", null, HttpStatus.OK.value()));
    }

    // Tìm kiếm chuyến xe buýt
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<BusRoute>>> searchBusRoutes(
            @RequestParam(required = false) String startPoint,
            @RequestParam(required = false) String endPoint,
            @RequestParam(required = false) Integer id) {
        List<BusRoute> busRoutes = busRouteService.searchBusRoutes(startPoint, endPoint, id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Kết quả", busRoutes, HttpStatus.OK.value()));
    }
}
