package top.ltcnb.shoppingsystembackend.DO.dto;


import lombok.Data;
import top.ltcnb.shoppingsystembackend.DO.entity.Order;

@Data
public class OrderBody {
    Long id;
    Byte paymentType;
    String tel;
    String address;
    String append;

    public Order toOrder() {
        return new Order(id, null, null, paymentType, tel, address, null, null, append, null);
    }
}
