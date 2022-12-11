package top.ltcnb.shoppingsystembackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.ltcnb.shoppingsystembackend.DO.R;
import top.ltcnb.shoppingsystembackend.DO.entity.Comment;

import javax.servlet.http.HttpServletRequest;


public interface ICommentService extends IService<Comment> {
    R<Object> postComment(HttpServletRequest request, String commodityId, String content);


    R<Object> listComments(String commodityId);

}
