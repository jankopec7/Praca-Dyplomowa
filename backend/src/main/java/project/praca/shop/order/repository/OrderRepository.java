package project.praca.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.praca.shop.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
