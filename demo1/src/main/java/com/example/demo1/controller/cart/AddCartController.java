package com.example.demo1.controller.cart;

import com.example.demo1.model.CartItem;
import com.example.demo1.model.User;
import com.example.demo1.service.CartService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "AddCartController", value = "/AddCart")
public class AddCartController extends HttpServlet {
    private CartService cartService = new CartService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");

        if (cart == null) {
            cart = new HashMap<>();
            session.setAttribute("cart", cart);
        }

        try {
            int id = (request.getParameter("id") != null) ? Integer.parseInt(request.getParameter("id")) : 0;

            if ("add".equals(action)) {
                cartService.addToCart(cart, id, 1);
                response.sendRedirect("AddCart?action=view");
            }
            else if ("update".equals(action)) {
                int num = Integer.parseInt(request.getParameter("num"));
                cartService.updateQuantity(cart, id, num);
                response.sendRedirect("AddCart?action=view");
            }
            else if ("delete".equals(action)) {
                cartService.removeItem(cart, id);
                response.sendRedirect("AddCart?action=view");
            }
            else if ("buyNow".equals(action)) {
                cartService.addToCart(cart, id, 1);
                response.sendRedirect("AddCart?action=checkout");
            }
            else if ("view".equals(action)) {
                request.setAttribute("totalAmount", cartService.calculateTotal(cart));
                request.getRequestDispatcher("/cart.jsp").forward(request, response);
            }
            else if ("checkout".equals(action)) {
                double total = cartService.calculateTotal(cart);
                request.setAttribute("totalAmount", total);
                request.getRequestDispatcher("/thanhToan.jsp").forward(request, response);
            }
        } catch (Exception e) {
            response.sendRedirect("home.jsp");
        }
    }
}
