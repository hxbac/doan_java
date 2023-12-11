package com.doan.shop.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CheckAdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        if (role.equals("ADMIN")) {
            return true;
        }
        response.sendRedirect(request.getContextPath() + "/");
        return false;
    }
}
