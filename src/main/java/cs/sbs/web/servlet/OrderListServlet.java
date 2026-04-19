package cs.sbs.web.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import cs.sbs.web.model.Order;
import cs.sbs.web.model.DataManager;

/**
 * 订单列表Servlet - 显示所有订单
 */
public class OrderListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter out = resp.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"zh-CN\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>我的订单</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; max-width: 600px; margin: 20px auto; padding: 20px; }");
        out.println("h1 { color: #333; }");
        out.println(".order-item { background: #f9f9f9; padding: 15px; margin: 10px 0; border-radius: 5px; }");
        out.println("a { color: #2196F3; text-decoration: none; }");
        out.println("a:hover { text-decoration: underline; }");
        out.println(".back-link { display: inline-block; margin-top: 20px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        out.println("<h1>我的订单</h1>");

        List<Order> orders = DataManager.getAllOrders();

        if (orders.isEmpty()) {
            out.println("<p>暂无订单</p>");
        } else {
            for (Order order : orders) {
                out.println("<div class=\"order-item\">");
                out.println("<strong>Order #" + order.getId() + "</strong>");
                out.println(" - " + order.getFood() + " x" + order.getQuantity());
                out.println("<br><a href=\"/order/" + order.getId() + "\">查看详情</a>");
                out.println("</div>");
            }
        }

        out.println("<div class=\"back-link\">");
        out.println("<a href=\"/\">返回首页</a>");
        out.println("</div>");

        out.println("</body>");
        out.println("</html>");
    }
}