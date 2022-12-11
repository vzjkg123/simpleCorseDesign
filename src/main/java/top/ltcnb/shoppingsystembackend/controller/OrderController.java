package top.ltcnb.shoppingsystembackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.ltcnb.shoppingsystembackend.DO.R;
import top.ltcnb.shoppingsystembackend.DO.dto.OrderBody;
import top.ltcnb.shoppingsystembackend.service.IOrderService;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    final IOrderService orderService;

    @PostMapping("/createOrder")
    public R<Object> createOrder(HttpServletRequest request, @RequestParam String commodityId) {
        return orderService.createOrder(request, commodityId);
    }

    @GetMapping("/queryOrders")
    public R<Object> queryUserOrders(HttpServletRequest request) {
        return orderService.listUserOrder(request);
    }


    @PostMapping("/updateOrder")
    public R<Object> update(HttpServletRequest request, @RequestBody OrderBody orderBody) {
        return orderService.writeOrderDetail(request, orderBody);
    }
}
