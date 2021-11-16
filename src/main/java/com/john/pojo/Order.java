package com.john.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: John
 * @Date: 2021/11/16/18:07
 * @Description:
 */



public class Order {

    private String orderId;
    private String createTime;
    private BigDecimal price;

    private Integer status = noSendStatus;
    private Integer userId;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", createTime='" + createTime + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }



    // o未发货 1发货 2收货
    public static final Integer noSendStatus = 0;
    public static final Integer sendStatus = 1;
    public static final Integer receiveStatus = 2;

    public Order(String orderId, String createTime, BigDecimal price, Integer status, Integer userId) {
        this.orderId = orderId;
        this.createTime = createTime;
        this.price = price;
        this.status = status;
        this.userId = userId;
    }




}
