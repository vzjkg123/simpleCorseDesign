package top.ltcnb.shoppingsystembackend.service;

import top.ltcnb.shoppingsystembackend.DO.R;
import top.ltcnb.shoppingsystembackend.DO.dto.CommodityBody;
import top.ltcnb.shoppingsystembackend.DO.dto.QueryCommentBody;
import top.ltcnb.shoppingsystembackend.DO.dto.QueryCommodityBody;
import top.ltcnb.shoppingsystembackend.DO.dto.QueryUserBody;

import javax.servlet.http.HttpServletRequest;

public interface IAdministerService {
    boolean isAdminister(String id, String token);
    boolean isAdminister(HttpServletRequest request);

    R<Object> queryUser(HttpServletRequest request, QueryUserBody queryUserBody);


    R<Object> deleteUser(HttpServletRequest request, String targetId);

    R<Object> addCommodity(HttpServletRequest request, CommodityBody commodityBody);

    R<Object> queryCommodity(HttpServletRequest request, String key);

    R<Object> deleteCommodity(HttpServletRequest request, String targetId);

    R<Object> queryOrders(HttpServletRequest request, String orderId);

    R<Object> deleteOrder(HttpServletRequest request, String targetId);

    R<Object> queryComment(HttpServletRequest request, QueryCommentBody queryCommentBody);

    R<Object> deleteComment(HttpServletRequest request, String targetId);


}
