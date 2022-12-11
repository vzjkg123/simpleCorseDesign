package top.ltcnb.shoppingsystembackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.ltcnb.shoppingsystembackend.DO.R;
import top.ltcnb.shoppingsystembackend.DO.dto.OrderBody;
import top.ltcnb.shoppingsystembackend.DO.entity.Order;

import javax.servlet.http.HttpServletRequest;

public interface IOrderService extends IService<Order> {
    R<Object> createOrder(HttpServletRequest request, String commodityId);

    R<Object> writeOrderDetail(HttpServletRequest request, OrderBody orderBody);

    R<Object> listUserOrder(HttpServletRequest request);
}
