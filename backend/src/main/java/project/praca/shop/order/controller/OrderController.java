package project.praca.shop.order.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.praca.shop.order.controller.dto.NotificationDto;
import project.praca.shop.order.model.Order;
import project.praca.shop.common.model.OrderStatus;
import project.praca.shop.order.model.dto.*;
import project.praca.shop.order.service.OrderService;
import project.praca.shop.order.service.PaymentService;
import project.praca.shop.order.service.ShipmentService;;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
@Validated
public class OrderController {

    private final OrderService orderService;
    private final ShipmentService shipmentService;
    private final PaymentService paymentService;

    @PostMapping
    public OrderSummary placeOrder(@RequestBody OrderDto orderDto, @AuthenticationPrincipal Long userId) {
        return orderService.placeOrder(orderDto, userId);
    }

    @GetMapping("/initData")
    public InitOrder initData() {
        return InitOrder.builder()
                .shipment(shipmentService.getShipments())
                .payment(paymentService.getPayments())
                .build();
    }

    @GetMapping
    public List<OrderListDto> getOrders(@AuthenticationPrincipal Long userId){
        if(userId == null){
            throw new IllegalArgumentException("Brak użytkownika");
        }
        return orderService.getOrdersForCustomer(userId);
    }

    @GetMapping("/notification/{orderHash}")
    public NotificationDto notificationShow(@PathVariable @Length(max = 12) String orderHash){
        Order order = orderService.getOrderByOrderHash(orderHash);
        return new NotificationDto(order.getOrderStatus() == OrderStatus.PAID);
    }

    @PostMapping("/notification/{orderHash}")
    public void notificationReceive(@PathVariable @Length(max = 12) String orderHash,
                                    @RequestBody NotificationReceiveDto receiveDto,
                                    HttpServletRequest request
    ){
        String forwardedAddress = request.getHeader("x-forwarder-for");
        orderService.receiveNotification(orderHash, receiveDto,
                StringUtils.isNotEmpty(forwardedAddress) ? forwardedAddress: request.getRemoteAddr());
    }
}