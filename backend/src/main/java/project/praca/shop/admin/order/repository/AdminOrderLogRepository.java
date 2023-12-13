package project.praca.shop.admin.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.praca.shop.admin.order.model.AdminOrderLog;

public interface AdminOrderLogRepository extends JpaRepository<AdminOrderLog, Long> {
}
