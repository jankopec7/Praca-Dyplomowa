package project.praca.shop.admin.order.controller.dto;

import lombok.Builder;
import lombok.Getter;
import project.praca.shop.admin.order.model.AdminOrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class AdminOrderDto {
    private Long id;
    private LocalDateTime placeDate;
    private AdminOrderStatus orderStatus;
    private BigDecimal grossValue;
}