package project.praca.shop.admin.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.praca.shop.admin.order.model.AdminOrder;
import project.praca.shop.admin.order.model.AdminOrderStatus;
import project.praca.shop.admin.order.repository.AdminOrderRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminExportService {

    private final AdminOrderRepository orderRepository;

    public List<AdminOrder> exportOrders(LocalDateTime from, LocalDateTime to, AdminOrderStatus orderStatus) {
        return orderRepository.findAllByPlaceDateIsBetweenAndOrderStatus(from, to, orderStatus);
    }
}
