package com.njxzc.po;

import cn.hutool.core.date.DateTime;

import java.util.Date;

public class Order {
    //basetable
    private Integer id;
    private Integer busertable_id;
    private Double amount;
    private String status;
    private DateTime orderdata;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", busertable_id=" + busertable_id +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", orderdata='" + orderdata + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBusertable_id() {
        return busertable_id;
    }

    public void setBusertable_id(Integer busertable_id) {
        this.busertable_id = busertable_id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DateTime getOrderdata() {
        return orderdata;
    }

    public void setOrderdata(DateTime orderdata) {
        this.orderdata = orderdata;
    }
}
