package kz.javalab.va.entity.order;

import kz.javalab.va.entity.Entity;

import java.io.Serializable;

public class Order extends Entity implements Serializable {
    private Integer sumOfOrder;
    private Integer userId;
    private String address;
    private String phone;
    private Status status;

    public Integer getSumOfOrder() {
        return sumOfOrder;
    }

    public void setSumOfOrder(Integer sumOfOrder) {
        this.sumOfOrder = sumOfOrder;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "sumOfOrder=" + sumOfOrder +
                ", userId=" + userId +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                '}';
    }
}
