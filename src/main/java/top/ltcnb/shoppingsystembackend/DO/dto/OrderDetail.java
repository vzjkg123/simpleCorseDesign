package top.ltcnb.shoppingsystembackend.DO.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import top.ltcnb.shoppingsystembackend.DO.entity.Order;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OrderDetail extends Order {

    public OrderDetail(Order order) {
        super(order.getId(), order.getCommodityId(),
                order.getUid(), order.getPaymentType(),
                order.getTel(), order.getAddress(),
                order.getTransportType(), order.getIsCheck(),
                order.getAppend(), order.getDate());
    }

    String commodityName;

    BigDecimal price;
    String url;
}
