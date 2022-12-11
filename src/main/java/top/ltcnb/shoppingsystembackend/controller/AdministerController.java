package top.ltcnb.shoppingsystembackend.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import top.ltcnb.shoppingsystembackend.DO.R;
import top.ltcnb.shoppingsystembackend.DO.dto.CommodityBody;
import top.ltcnb.shoppingsystembackend.DO.dto.QueryCommentBody;
import top.ltcnb.shoppingsystembackend.DO.dto.QueryCommodityBody;
import top.ltcnb.shoppingsystembackend.DO.dto.QueryUserBody;
import top.ltcnb.shoppingsystembackend.service.IAdministerService;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/administer")
public class AdministerController {
    final IAdministerService administerService;

    @PostMapping("queryUser")
    public R<Object> queryUser(HttpServletRequest request, @RequestBody QueryUserBody queryUserBody) {
        return administerService.queryUser(request, queryUserBody);
    }

    @PostMapping("addCommodity")
    public R<Object> addCommodity(HttpServletRequest request, CommodityBody commodityBody) {
        return administerService.addCommodity(request, commodityBody);
    }

    @PostMapping("deleteCommodity")
    public R<Object> deleteCommodity(HttpServletRequest request, @RequestParam String targetId) {
        return administerService.deleteCommodity(request, targetId);
    }

    @GetMapping("queryCommodity/{keyWord}")
    public R<Object> queryCommodity(HttpServletRequest request, @PathVariable String keyWord) {
        return administerService.queryCommodity(request, keyWord);
    }

    @PostMapping("queryComment")
    public R<Object> queryComment(HttpServletRequest request,@RequestBody QueryCommentBody queryCommentBody) {
        return administerService.queryComment(request, queryCommentBody);
    }

    @PostMapping("deleteComment")
    public R<Object> deleteComment(HttpServletRequest request, @RequestParam String targetId) {
        return administerService.deleteComment(request, targetId);

    }

    @PostMapping("deleteUser")
    public R<Object> queryComment(HttpServletRequest request, @RequestParam String targetId) {
        return administerService.deleteUser(request, targetId);
    }

    @GetMapping("queryOrders")
    public R<Object> queryOrders(HttpServletRequest request) {
        return administerService.queryOrders(request, "");
    }

    @PostMapping("deleteOrder")
    public R<Object> deleteOrder(HttpServletRequest request, @RequestParam String targetId) {
        return administerService.deleteOrder(request, targetId);
    }

}
