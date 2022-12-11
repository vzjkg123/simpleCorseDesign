package top.ltcnb.shoppingsystembackend.service.serviceImpl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import top.ltcnb.shoppingsystembackend.DO.R;
import top.ltcnb.shoppingsystembackend.DO.dto.CommodityBody;
import top.ltcnb.shoppingsystembackend.DO.dto.QueryCommentBody;
import top.ltcnb.shoppingsystembackend.DO.dto.QueryUserBody;
import top.ltcnb.shoppingsystembackend.DO.entity.Comment;
import top.ltcnb.shoppingsystembackend.DO.entity.Commodity;
import top.ltcnb.shoppingsystembackend.DO.entity.Order;
import top.ltcnb.shoppingsystembackend.DO.entity.UserInfo;
import top.ltcnb.shoppingsystembackend.property.ErrorInfo;
import top.ltcnb.shoppingsystembackend.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdministerServiceImpl extends UserServiceImpl implements IAdministerService {

    final ICommodityService commodityService;
    final ICommentService commentService;
    final IOrderService orderService;
    final IUserService userService;


    public R<Object> queryUser(HttpServletRequest request, QueryUserBody queryUserBody) {
        if (!isAdminister(request)) return R.failed(ErrorInfo.UNAUTHENTIC);

        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(queryUserBody.getTargetId()))
            queryWrapper.eq(UserInfo::getId, queryUserBody.getTargetId());
        if (!StringUtils.isEmpty(queryUserBody.getUsername()))
            queryWrapper.or().like(UserInfo::getUsername, queryUserBody.getUsername());
        if (!StringUtils.isEmpty(queryUserBody.getEmail()))
            queryWrapper.or().like(UserInfo::getEmail, queryUserBody.getEmail());
        List<UserInfo> res = userService.list(queryWrapper);
        res = res.stream().map(e -> {
            e.setPassword("");
            return e;
        }).collect(Collectors.toList());
        return R.success(res);


    }

    @Override
    public R<Object> deleteUser(HttpServletRequest request, String targetId) {
        if (!isAdminister(request)) return R.failed(ErrorInfo.UNAUTHENTIC);

        if (userService.removeById(targetId)) {
            return R.success("Operate success!");
        }
        return R.failed(ErrorInfo.DATABASE_ERROR);
    }

    @Override
    @Deprecated
    public R<Object> queryCommodity(HttpServletRequest request, String keyWord) {
        if (!StringUtils.isEmpty(keyWord))
            return R.success(commodityService.list(new LambdaQueryWrapper<Commodity>().like(Commodity::getCommodityName, keyWord)));
        return R.success(commodityService.list());
    }

    @Override
    public R<Object> addCommodity(HttpServletRequest request, CommodityBody commodityBody) {
        if (commodityService.save(commodityBody.toCommodity())) {
            return R.success("success!");
        }
        return R.failed(ErrorInfo.DATABASE_ERROR);
    }

    @Override
    public R<Object> deleteCommodity(HttpServletRequest request, String targetId) {
        if (isAdminister(request)) {
            if (commodityService.removeById(targetId)) {
                return R.success("Operate success!");
            }
            return R.failed(ErrorInfo.DATABASE_ERROR);
        }
        return R.failed(ErrorInfo.UNAUTHENTIC);
    }

    @Override
    public R<Object> queryOrders(HttpServletRequest request, String orderId) {
        if (!isAdminister(request)) return R.failed(ErrorInfo.UNAUTHENTIC);
        if (StringUtils.isEmpty(orderId))
            return R.success(orderService.list(new LambdaQueryWrapper<Order>().orderByDesc(Order::getDate)));
        return R.success(orderService.getById(orderId));
    }

    @Override
    public R<Object> deleteOrder(HttpServletRequest request, String targetId) {
        if (!isAdminister(request)) return R.failed(ErrorInfo.UNAUTHENTIC);
        System.out.println(targetId);
        if (orderService.removeById(targetId)) {
            return R.success("Operate success!");
        }
        return R.failed(ErrorInfo.DATABASE_ERROR);
    }

    @Override
    public R<Object> queryComment(HttpServletRequest request, QueryCommentBody queryCommentBody) {
        if (!isAdminister(request)) return R.failed(ErrorInfo.UNAUTHENTIC);
        System.out.println("postId:"+queryCommentBody.getPostId());
        System.out.println(queryCommentBody);
        if (!StringUtils.isEmpty(queryCommentBody.getPostId())) {
            return R.success(getById(queryCommentBody.getPostId()));
        }
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(queryCommentBody.getUid())) {
            wrapper.eq(Comment::getUid, queryCommentBody.getUid());
        }
        if (!StringUtils.isEmpty(queryCommentBody.getContent())) {
            wrapper.like(Comment::getContent, queryCommentBody.getContent());
        }
        return R.success(commentService.list(wrapper));
    }

    @Override
    public R<Object> deleteComment(HttpServletRequest request, String targetId) {
        if (isAdminister(request)) {
            if (commentService.removeById(targetId)) {
                return R.success("Operate success!");
            }
            return R.failed(ErrorInfo.DATABASE_ERROR);
        }
        return R.failed(ErrorInfo.UNAUTHENTIC);
    }


    @Override
    public boolean isAdminister(String id, String token) {
        return authenticToken(id, token) && administer.contains(id);
    }

    public boolean isAdminister(HttpServletRequest request) {
        String id = request.getHeader("uid");
        String token = request.getHeader("token");
        if (!authenticToken(id, token)) {
            System.out.println("id:" + id);
            System.out.println("token" + token);
            return false;
        }
        if (!administer.contains(id)) {
            System.out.println("不属于管理员队列");
            return false;
        }
        return true;
    }
}
