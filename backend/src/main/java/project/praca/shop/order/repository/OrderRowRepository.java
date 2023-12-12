package project.praca.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.praca.shop.order.model.OrderRow;

public interface OrderRowRepository extends JpaRepository<OrderRow, Long> {
}
