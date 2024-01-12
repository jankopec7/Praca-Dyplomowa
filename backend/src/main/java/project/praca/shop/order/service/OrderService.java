package project.praca.shop.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.praca.shop.common.model.Cart;
import project.praca.shop.common.repository.CartItemRepository;
import project.praca.shop.common.repository.CartRepository;
import project.praca.shop.common.email.EmailClientService;
import project.praca.shop.common.model.OrderStatus;
import project.praca.shop.order.model.Order;
import project.praca.shop.order.model.OrderLog;
import project.praca.shop.order.model.Payment;
import project.praca.shop.order.model.PaymentType;
import project.praca.shop.order.model.Shipment;
import project.praca.shop.order.model.dto.NotificationReceiveDto;
import project.praca.shop.order.model.dto.OrderDto;
import project.praca.shop.order.model.dto.OrderListDto;
import project.praca.shop.order.model.dto.OrderSummary;

import project.praca.shop.order.repository.OrderLogRepository;
import project.praca.shop.order.repository.OrderRepository;
import project.praca.shop.order.repository.OrderRowRepository;
import project.praca.shop.order.repository.PaymentRepository;
import project.praca.shop.order.repository.ShipmentRepository;
import project.praca.shop.order.service.payment.p24.PaymentMethodP24;

import java.time.LocalDateTime;
import java.util.List;

import static project.praca.shop.order.service.mapper.OrderEmailMessageMapper.createEmailMessage;
import static project.praca.shop.order.service.mapper.OrderDtoMapper.mapToOrderListDto;
import static project.praca.shop.order.service.mapper.OrderMapper.createNewOrder;
import static project.praca.shop.order.service.mapper.OrderMapper.createOrderSummary;
import static project.praca.shop.order.service.mapper.OrderMapper.mapToOrderRow;
import static project.praca.shop.order.service.mapper.OrderMapper.mapToOrderRowWithQuantity;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderRowRepository orderRowRepository;
    private final ShipmentRepository shipmentRepository;
    private final PaymentRepository paymentRepository;
    private final EmailClientService emailClientService;
    private final PaymentMethodP24 paymentMethodP24;
    private final OrderLogRepository orderLogRepository;

    @Transactional
    public OrderSummary placeOrder(OrderDto orderDto, Long userId) {
        Cart cart = cartRepository.findById(orderDto.getCartId()).orElseThrow();
        Shipment shipment = shipmentRepository.findById(orderDto.getShipmentId()).orElseThrow();
        Payment payment = paymentRepository.findById(orderDto.getPaymentId()).orElseThrow();
        Order newOrder = orderRepository.save(createNewOrder(orderDto, cart, shipment, payment, userId));
        saveOrderRows(cart, newOrder.getId(), shipment);
        clearOrderCart(orderDto);
        sendConfirmEmail(newOrder);
        String redirectUrl = initPaymentIfNeeded(newOrder);
        return createOrderSummary(payment, newOrder, redirectUrl);
    }

    private String initPaymentIfNeeded(Order newOrder) {
        if(newOrder.getPayment().getType() == PaymentType.P24_ONLINE){
            return paymentMethodP24.initPayment(newOrder);
        }
        return null;
    }

    private void sendConfirmEmail(Order newOrder) {
        emailClientService.getInstance()
                .send(newOrder.getEmail(),
                        "Twoje zamówienie zostało przyjęte",
                        createEmailMessage(newOrder));
    }

    private void clearOrderCart(OrderDto orderDto) {
        cartItemRepository.deleteByCartId(orderDto.getCartId());
        cartRepository.deleteCartById(orderDto.getCartId());
    }

    private void saveOrderRows(Cart cart, Long orderId, Shipment shipment) {
        saveProductRows(cart, orderId);
        saveShipmentRow(orderId, shipment);
    }

    private void saveShipmentRow(Long orderId, Shipment shipment) {
        orderRowRepository.save(mapToOrderRow(orderId, shipment));
    }

    private void saveProductRows(Cart cart, Long orderId) {
        cart.getItems().stream()
                .map(cartItem -> mapToOrderRowWithQuantity(orderId, cartItem)
                )
                .peek(orderRowRepository::save)
                .toList();
    }

    public List<OrderListDto> getOrdersForCustomer(Long userId) {
        return mapToOrderListDto(orderRepository.findByUserId(userId));
    }

    public Order getOrderByOrderHash(String orderHash) {
        return orderRepository.findByOrderHash(orderHash).orElseThrow();
    }

    @Transactional
    public void receiveNotification(String orderHash, NotificationReceiveDto receiveDto, String remoteAddr) {
        Order order = getOrderByOrderHash(orderHash);
        String status = paymentMethodP24.receiveNotification(order, receiveDto, remoteAddr);
        if(status.equals("success")){
            OrderStatus oldStatus = order.getOrderStatus();
            order.setOrderStatus(OrderStatus.PAID);
            orderLogRepository.save(OrderLog.builder()
                    .created(LocalDateTime.now())
                    .orderId(order.getId())
                    .note("Opłacono zamówienie przez Przelewy24, id płatności: " + receiveDto.getStatement() +
                            " , zmieniono status z " + oldStatus.getValue() + " na " + order.getOrderStatus().getValue())
                    .build());
        }
    }
}
