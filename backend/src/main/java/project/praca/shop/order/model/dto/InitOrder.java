package project.praca.shop.order.model.dto;

import lombok.Builder;
import lombok.Getter;
import project.praca.shop.order.model.Payment;
import project.praca.shop.order.model.Shipment;

import java.util.List;

@Getter
@Builder
public class InitOrder {
    private List<Shipment> shipment;
    private List<Payment> payment;
}
