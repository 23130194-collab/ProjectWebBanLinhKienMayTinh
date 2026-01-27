package com.example.demo1.controller.admin;

import com.example.demo1.dao.NotificationDao;
import com.example.demo1.model.Notification;
import com.example.demo1.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebFilter(urlPatterns = {"/admin/*"})
public class AdminNotificationServlet implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);

        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null && user.getRole() == 1) {
                try {
                    NotificationDao dao = new NotificationDao();

                    List<Notification> notiList = dao.getForAdmin();

                    int unreadCount = dao.countUnreadAdmin();

                    req.setAttribute("adminNotiList", notiList);
                    req.setAttribute("adminUnreadCount", unreadCount);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        chain.doFilter(request, response);
    }
}