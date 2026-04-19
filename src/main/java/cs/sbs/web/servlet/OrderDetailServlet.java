package cs.sbs.web.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import cs.sbs.web.model.Order;
import cs.sbs.web.model.DataManager;

public class OrderDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 设置响应内容类型和编码
        resp.setContentType("text/plain; charset=UTF-8");

        PrintWriter out = resp.getWriter();

        // 获取路径参数（如 /order/1001 中的 1001）
        String pathInfo = req.getPathInfo();

        // 检查路径参数是否存在
        if (pathInfo == null || pathInfo.equals("/")) {
            out.println("Error: order ID is required");
            return;
        }

        // 去掉开头的斜杠，获取订单ID
        String orderIdStr = pathInfo.substring(1);

        // 解析订单ID
        int orderId;
        try {
            orderId = Integer.parseInt(orderIdStr);
        } catch (NumberFormatException e) {
            out.println("Error: order ID must be a valid number");
            return;
        }

        // 查找订单
        Order order = DataManager.findOrderById(orderId);

        // 异常处理：订单不存在
        if (order == null) {
            out.println("Error: order not found");
            return;
        }

        // 输出订单详情
        out.println("Order Detail");
        out.println();
        out.println("Order ID: " + order.getId());
        out.println("Customer: " + order.getCustomer());
        out.println("Food: " + order.getFood());
        out.println("Quantity: " + order.getQuantity());
    }
}
