package top.ltcnb.shoppingsystembackend.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import top.ltcnb.shoppingsystembackend.DO.R;
import top.ltcnb.shoppingsystembackend.DO.entity.Comment;
import top.ltcnb.shoppingsystembackend.DO.mapper.CommentMapper;
import top.ltcnb.shoppingsystembackend.property.ErrorInfo;
import top.ltcnb.shoppingsystembackend.service.ICommentService;
import top.ltcnb.shoppingsystembackend.service.IUserService;

import javax.servlet.http.HttpServletRequest;

import static top.ltcnb.shoppingsystembackend.util.HeaderUtil.getId;


@Service

public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {
    IUserService userService;

    @Autowired
    @Qualifier("userServiceImpl")
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }


    @Override
    public R<Object> postComment(HttpServletRequest request, String commodityId, String content) {
        Comment comment = new Comment();
        comment.setUid(Long.valueOf(getId(request)));
        comment.setCommodityId(Long.valueOf(commodityId));
        comment.setContent(content);
        if (userService.authenticToken(request)) {
            if (save(comment)) {
                return R.success("Post success!");
            }
            return R.failed(ErrorInfo.DATABASE_ERROR);
        }
        return R.failed(ErrorInfo.UNAUTHENTIC);
    }

    @Override
    public R<Object> listComments(String commodityId) {
        return R.success(list(new LambdaQueryWrapper<Comment>().eq(Comment::getCommodityId, commodityId)));
    }
}
