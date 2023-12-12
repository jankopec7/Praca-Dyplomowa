package project.praca.shop.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.praca.shop.order.model.Payment;
import project.praca.shop.order.repository.PaymentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }
}
