package project.praca.shop.admin.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.praca.shop.admin.order.model.dto.AdminOrderStats;
import project.praca.shop.admin.order.service.AdminOrderStatsService;

@RestController
@RequestMapping("/admin/orders/stats")
@RequiredArgsConstructor
public class AdminOrderStatsController {

    private final AdminOrderStatsService adminOrderStatsService;

    @GetMapping
    public AdminOrderStats getOrderStatistics(){
        return adminOrderStatsService.getStatistics();
    }
}