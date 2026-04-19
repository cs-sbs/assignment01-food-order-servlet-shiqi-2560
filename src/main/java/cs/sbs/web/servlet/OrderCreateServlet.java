package cs.sbs.web.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import cs.sbs.web.model.Order;
import cs.sbs.web.model.DataManager;

public class OrderCreateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 设置请求和响应编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain; charset=UTF-8");

        PrintWriter out = resp.getWriter();

        // 获取表单参数
        String customer = req.getParameter("customer");
        String food = req.getParameter("food");
        String quantityStr = req.getParameter("quantity");

        // 异常处理：检查参数是否为空
        if (customer == null || customer.trim().isEmpty()) {
            out.println("Error: customer name is required");
            return;
        }

        if (food == null || food.trim().isEmpty()) {
            out.println("Error: food name is required");
            return;
        }

        if (quantityStr == null || quantityStr.trim().isEmpty()) {
            out.println("Error: quantity is required");
            return;
        }

        // 异常处理：检查数量是否为合法数字
        int quantity;
        try {
            quantity = Integer.parseInt(quantityStr.trim());
            if (quantity <= 0) {
                out.println("Error: quantity must be a positive number");
                return;
            }
        } catch (NumberFormatException e) {
            out.println("Error: quantity must be a valid number");
            return;
        }

        // 创建订单
        Order order = DataManager.createOrder(customer.trim(), food.trim(), quantity);

        // 返回结果
        out.println("Order Created: " + order.getId());
    }
}
