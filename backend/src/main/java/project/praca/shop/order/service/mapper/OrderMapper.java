package project.praca.shop.order.service.mapper;

import org.apache.commons.lang3.RandomStringUtils;
import project.praca.shop.common.model.Cart;
import project.praca.shop.common.model.CartItem;
import project.praca.shop.order.model.Order;
import project.praca.shop.order.model.OrderRow;
import project.praca.shop.common.model.OrderStatus;
import project.praca.shop.order.model.Payment;
import project.praca.shop.order.model.Shipment;
import project.praca.shop.order.model.dto.OrderDto;
import project.praca.shop.order.model.dto.OrderSummary;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderMapper {
    public static Order createNewOrder(OrderDto orderDto, Cart cart, Shipment shipment, Payment payment, Long userId) {
        return Order.builder()
                .firstname(orderDto.getFirstname())
                .lastname(orderDto.getLastname())
                .street(orderDto.getStreet())
                .zipcode(orderDto.getZipcode())
                .city(orderDto.getCity())
                .email(orderDto.getEmail())
                .phone(orderDto.getPhone())
                .placeDate(LocalDateTime.now())
                .orderStatus(OrderStatus.NEW)
                .grossValue(calculateGrossValue(cart.getItems(), shipment))
                .payment(payment)
                .userId(userId)
                .orderHash(RandomStringUtils.randomAlphanumeric(12))
                .build();
    }

    private static BigDecimal calculateGrossValue(List<CartItem> items, Shipment shipment) {
        return items.stream()
                .map(cartItem -> (cartItem.getProduct().getSalePrice() != null ? cartItem.getProduct().getSalePrice() :cartItem.getProduct().getPrice()).multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .add(shipment.getPrice());
    }

    public static OrderSummary createOrderSummary(Payment payment, Order newOrder, String redirectUrl) {
        return OrderSummary.builder()
                .id(newOrder.getId())
                .placeDate(newOrder.getPlaceDate())
                .status(newOrder.getOrderStatus())
                .grossValue(newOrder.getGrossValue())
                .payment(payment)
                .redirectUrl(redirectUrl)
                .build();
    }

    public static OrderRow mapToOrderRow(Long orderId, Shipment shipment) {
        return OrderRow.builder()
                .quantity(1)
                .price(shipment.getPrice())
                .shipmentId(shipment.getId())
                .orderId(orderId)
                .build();
    }

    public static OrderRow mapToOrderRowWithQuantity(Long orderId, CartItem cartItem) {
        return OrderRow.builder()
                .quantity(cartItem.getQuantity())
                .productId(cartItem.getProduct().getId())
                .price((cartItem.getProduct().getSalePrice() != null? cartItem.getProduct().getSalePrice():cartItem.getProduct().getPrice()))
                .orderId(orderId)
                .build();
    }
}