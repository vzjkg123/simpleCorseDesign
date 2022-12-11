package top.ltcnb.shoppingsystembackend.service;

import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import top.ltcnb.shoppingsystembackend.DO.R;
import top.ltcnb.shoppingsystembackend.DO.dto.QueryUserBody;
import top.ltcnb.shoppingsystembackend.DO.dto.RegisterBody;
import top.ltcnb.shoppingsystembackend.DO.entity.UserInfo;

/**
 * @author tiancai
 */
public interface IUserService extends IService<UserInfo> {


    R<Object> register(RegisterBody registerBody);

    /**
     * 用户登录接口
     *
     * @param email    user email
     * @param password user password
     */
    R<Object> login(String email, String password);

    /**
     * 用户登出接口
     *
     * @param request 从请求头提取用户token
     */
    R<Object> logout(HttpServletRequest request);

    /**
     * 获取用户信息
     *
     * @param request 从请求头提取用户token
     */
    R<Object> getUserInfo(HttpServletRequest request);


    boolean authenticToken(HttpServletRequest request);
}
