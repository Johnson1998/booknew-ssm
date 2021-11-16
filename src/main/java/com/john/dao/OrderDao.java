package com.john.dao;

import com.john.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: John
 * @Date: 2021/11/16/18:21
 * @Description:
 */
@Mapper
public interface OrderDao {
    public int saveOrder(Order order);

    public List<Order> queryOrders();

    public void changeOrderStatus(@Param("orderId") String orderId, @Param("status") Integer status);

    public List<Order> queryOrderByUserId(int userId);

}
