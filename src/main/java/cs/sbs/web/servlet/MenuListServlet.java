package cs.sbs.web.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import cs.sbs.web.model.MenuItem;
import cs.sbs.web.model.DataManager;

public class MenuListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 设置响应内容类型和编码
        resp.setContentType("text/plain; charset=UTF-8");

        PrintWriter out = resp.getWriter();

        // 获取搜索参数
        String searchName = req.getParameter("name");

        // 查询菜单
        List<MenuItem> items;
        if (searchName != null && !searchName.isEmpty()) {
            items = DataManager.searchMenuItems(searchName);
        } else {
            items = DataManager.getAllMenuItems();
        }

        // 输出菜单列表
        out.println("Menu List:");
        out.println();

        if (items.isEmpty()) {
            out.println("No menu items found.");
            return;
        }

        int index = 1;
        for (MenuItem item : items) {
            out.println(index + ". " + item.getName() + " - $" + item.getPrice());
            index++;
        }
    }
}
