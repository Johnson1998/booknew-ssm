package com.john.service;

import com.john.dao.BookDao;
import com.john.dao.OrderDao;
import com.john.dao.OrderItemDao;
import com.john.pojo.*;
import com.john.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: John
 * @Date: 2021/11/16/18:39
 * @Description:
 */
@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private BookDao bookDao;

    public String createOrder(Cart cart, Integer userId) {

        String orderId = System.currentTimeMillis() + "" + userId;
        Order order = new Order(orderId, DateUtils.DateToString(new Date()), cart.getTotalPrice(), 0, userId);

        orderDao.saveOrder(order);

        for (Map.Entry<Integer, CartItem>entry : cart.getItems().entrySet()){
            // 获取每一个商品项转化成为订单项保存到数据库
            CartItem cartItem = entry.getValue();
            // 转换为每一个订单项
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(),
                    cartItem.getTotalPrice(), orderId);
            // 保存订单项到数据库
            orderItemDao.saveOrderItem(orderItem);

            //更新库存和销量
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales( book.getSales() + cartItem.getCount());
            book.setStock( book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);

        }

        cart.clear();



        return orderId;
    }

    public List<Order> showAllOrders() {
        return orderDao.queryOrders();
    }

    public void sendOrder(String orderId) {
        orderDao.changeOrderStatus(orderId, Order.sendStatus);
    }

    public List<Order> queryOrderByUserId(int userId) {
        return orderDao.queryOrderByUserId(userId);
    }

    public List<OrderItem> queryOrderItemByOrderId(String orderId) {
        return orderItemDao.queryOrderItemByOrderId(orderId);
    }

    public void receiveOrder(String orderId) {
        orderDao.changeOrderStatus(orderId, Order.receiveStatus);
    }

}
