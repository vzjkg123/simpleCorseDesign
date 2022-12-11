package top.ltcnb.shoppingsystembackend.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import top.ltcnb.shoppingsystembackend.DO.R;
import top.ltcnb.shoppingsystembackend.DO.dto.OrderBody;
import top.ltcnb.shoppingsystembackend.DO.dto.OrderDetail;
import top.ltcnb.shoppingsystembackend.DO.entity.Commodity;
import top.ltcnb.shoppingsystembackend.DO.entity.Order;
import top.ltcnb.shoppingsystembackend.DO.mapper.OrderMapper;
import top.ltcnb.shoppingsystembackend.property.ErrorInfo;
import top.ltcnb.shoppingsystembackend.service.ICommodityService;
import top.ltcnb.shoppingsystembackend.service.IOrderService;
import top.ltcnb.shoppingsystembackend.service.IUserService;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static top.ltcnb.shoppingsystembackend.util.HeaderUtil.getId;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
    IUserService userService;
    ICommodityService commodityService;

    @Autowired
    public void setCommodityService(ICommodityService commodityService) {
        this.commodityService = commodityService;
    }


    @Autowired
    @Qualifier("userServiceImpl")
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }


    @Override
    public R<Object> createOrder(HttpServletRequest request, String commodityId) {
        if (!userService.authenticToken(request)) return R.failed(ErrorInfo.UNAUTHENTIC);

        Order order = new Order();
        order.setUid(Long.valueOf(getId(request)));
        order.setCommodityId(Long.valueOf(commodityId));
        if (save(order)) {
            return R.success("success!");
        }
        return R.failed(ErrorInfo.DATABASE_ERROR);
    }

    @Override
    public R<Object> writeOrderDetail(HttpServletRequest request, OrderBody orderBody) {
        if (!userService.authenticToken(request)) return R.failed(ErrorInfo.UNAUTHENTIC);
        Order order = Objects.requireNonNull(getById(orderBody.getId()));
        if (!order.getUid().toString().equals(getId(request))) return R.failed(ErrorInfo.UNAUTHENTIC);
        if (updateById(orderBody.toOrder())) return R.success("success!");

        return R.failed(ErrorInfo.DATABASE_ERROR);
    }

    @Override
    public R<Object> listUserOrder(HttpServletRequest request) {
        if (!userService.authenticToken(request)) return R.failed(ErrorInfo.UNAUTHENTIC);
        List<Order> orders = list(new LambdaQueryWrapper<Order>().eq(Order::getUid, getId(request)));
        List<OrderDetail> res = orders.stream().map(
                order -> {
                    OrderDetail detail = new OrderDetail(order);
                    Commodity commodity = commodityService.getById(order.getCommodityId());
                    detail.setUrl(commodity.getPhoto());
                    detail.setCommodityName(commodity.getCommodityName());
                    detail.setPrice(commodity.getPrice());
                    return detail;
                }
        ).collect(Collectors.toList());
        return R.success(res);
    }
}
