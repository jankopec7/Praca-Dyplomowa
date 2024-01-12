package project.praca.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.praca.shop.order.model.OrderLog;

public interface OrderLogRepository extends JpaRepository<OrderLog, Long> {
}

