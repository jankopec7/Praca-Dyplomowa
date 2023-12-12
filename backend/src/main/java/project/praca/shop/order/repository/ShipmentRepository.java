package project.praca.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.praca.shop.order.model.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
}
