package top.ltcnb.shoppingsystembackend.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.ltcnb.shoppingsystembackend.DO.R;
import top.ltcnb.shoppingsystembackend.DO.dto.RegisterBody;
import top.ltcnb.shoppingsystembackend.DO.entity.UserInfo;
import top.ltcnb.shoppingsystembackend.DO.mapper.UserInfoMapper;
import top.ltcnb.shoppingsystembackend.property.ErrorInfo;
import top.ltcnb.shoppingsystembackend.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static top.ltcnb.shoppingsystembackend.util.HeaderUtil.getId;
import static top.ltcnb.shoppingsystembackend.util.HeaderUtil.getToken;

/**
 * @author tiancai
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserService {
    final static ConcurrentHashMap<String, String> tokenStore = new ConcurrentHashMap<>();
    final static ArrayDeque<String> administer = new ArrayDeque<>();

    /**
     * 测试用例填充，测试完成应当删除
     */
    public UserServiceImpl() {
        tokenStore.put("test", "testToken");
        administer.add("test");
    }


    public R<Object> register(RegisterBody registerBody) {
        UserInfo user = registerBody.toUserInfo();
        if (save(user)) {
            return R.success("Register success");
        }
        return R.failed("Register failed!");
    }

    public R<Object> login(String email, String password) {
        UserInfo userInfo;
        userInfo = getOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getEmail, email).eq(UserInfo::getPassword, password));
        if (userInfo != null) {
            HashMap<String, String> res = new HashMap<>();
            String uid = userInfo.getId().toString();
            res.put("token", generateToken(uid));
            res.put("uid", uid);
            res.put("username", userInfo.getUsername());
            res.put("isAdminister", userInfo.getIsAdminister().toString());
            if (userInfo.getIsAdminister()) administer.add(uid);
            System.out.println(tokenStore);
            return R.success(res);
        }
        return R.failed("邮箱或密码错误");
    }

    public R<Object> logout(HttpServletRequest request) {
        System.out.println("remove token");
        String id = getId(request);
        String token = getToken(request);
        if (authenticToken(id, token))
            tokenStore.remove(id);
        return R.success("Logout success!");
    }


    public R<Object> getUserInfo(HttpServletRequest request) {
        String id = getId(request);
        String token = getToken(request);
        if (authenticToken(id, token))
            return R.success(getById(id));
        return R.failed(ErrorInfo.UNAUTHENTIC);
    }

    /**
     * 存放token并返回
     */
    private String generateToken(String id) {
        String token = UUID.randomUUID().toString();
        System.out.println("Generate token");
        tokenStore.put(id, token);
        return token;
    }

    public boolean authenticToken(HttpServletRequest request) {
        String id = getId(request);
        String token = getToken(request);
        return tokenStore.containsKey(id) && tokenStore.get(id).equals(token);
    }

    public boolean authenticToken(String id, String token) {
        if (!tokenStore.containsKey(id)) {
            System.out.println("tokenStore not contain:" + id);
            return false;
        }
        if (!tokenStore.get(id).equals(token)) {
            System.out.println("tokenStore:" + tokenStore.get(id));
            return false;
        }

        return true;
    }


}
