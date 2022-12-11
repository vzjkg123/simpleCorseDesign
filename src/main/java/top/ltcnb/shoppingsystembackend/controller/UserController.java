package top.ltcnb.shoppingsystembackend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import top.ltcnb.shoppingsystembackend.DO.R;
import top.ltcnb.shoppingsystembackend.DO.dto.RegisterBody;
import top.ltcnb.shoppingsystembackend.service.IUserService;

import javax.servlet.http.HttpServletRequest;


/**
 * @author tiancai
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    IUserService userService;
    @Autowired
    @Qualifier("userServiceImpl")
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }


    @PostMapping("login")
    public R<Object> login(@RequestParam String email, @RequestParam String password) {
        return userService.login(email, password);
    }

    @PostMapping("logout")
    public R<Object> logout(HttpServletRequest request) {
        return userService.logout(request);
    }


    @PostMapping("register")
    public R<Object> register(@RequestBody RegisterBody registerBody) {
        return userService.register(registerBody);
    }

}
