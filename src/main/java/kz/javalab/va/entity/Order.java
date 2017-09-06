package kz.javalab.va.entity;

import java.util.List;

public class Order extends Entity {
    private List<Food> food;
    private double sumOfOrder;
    private User client;
    private String address;
}
