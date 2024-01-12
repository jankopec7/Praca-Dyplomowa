package project.praca.shop.order.service.mapper;

import project.praca.shop.order.model.Order;
import project.praca.shop.order.model.dto.OrderListDto;

import java.util.List;

public class OrderDtoMapper {
    public static List<OrderListDto> mapToOrderListDto(List<Order> orders) {
        return orders.stream()
                .map(order -> new OrderListDto(
                        order.getId(),
                        order.getPlaceDate(),
                        order.getOrderStatus().getValue(),
                        order.getGrossValue()))
                .toList();
    }
}