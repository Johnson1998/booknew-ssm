package com.john.dao;

import com.john.pojo.OrderItem;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: John
 * @Date: 2021/11/13/11:27
 * @Description:
 */
public interface OrderItemDao {

    public int saveOrderItem(OrderItem orderItem);

    public List<OrderItem> queryOrderItemByOrderId(String orderId);

}
