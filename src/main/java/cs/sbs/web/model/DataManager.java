package cs.sbs.web.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据存储类，用于存储菜单和订单数据
 * 使用静态变量模拟数据库
 */
public class DataManager {

    // 菜单列表
    private static final List<MenuItem> menuItems = new ArrayList<>();

    // 订单列表
    private static final List<Order> orders = new ArrayList<>();

    // 订单ID生成器
    private static int nextOrderId = 1001;

    // 静态初始化块，初始化菜单数据
    static {
        menuItems.add(new MenuItem("Fried Rice", 8));
        menuItems.add(new MenuItem("Fried Noodles", 9));
        menuItems.add(new MenuItem("Burger", 10));
    }

    /**
     * 获取所有菜单项
     */
    public static List<MenuItem> getAllMenuItems() {
        return menuItems;
    }

    /**
     * 按名称搜索菜单项（模糊匹配）
     */
    public static List<MenuItem> searchMenuItems(String name) {
        List<MenuItem> result = new ArrayList<>();
        if (name == null || name.isEmpty()) {
            return menuItems;
        }
        for (MenuItem item : menuItems) {
            if (item.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * 创建新订单
     */
    public static Order createOrder(String customer, String food, int quantity) {
        Order order = new Order(nextOrderId++, customer, food, quantity);
        orders.add(order);
        return order;
    }

    /**
     * 根据ID查找订单
     */
    public static Order findOrderById(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    /**
     * 获取所有订单
     */
    public static List<Order> getAllOrders() {
        return orders;
    }
}