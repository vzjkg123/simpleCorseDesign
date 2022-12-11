package top.ltcnb.shoppingsystembackend.util;

import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class HeaderUtil {

    public static String getId(HttpServletRequest request) {
        return request.getHeader("uid");
    }

    public static String getToken(HttpServletRequest request) {
        return request.getHeader("token");
    }
}
