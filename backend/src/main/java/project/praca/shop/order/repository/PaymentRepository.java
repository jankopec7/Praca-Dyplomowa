package project.praca.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.praca.shop.order.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
