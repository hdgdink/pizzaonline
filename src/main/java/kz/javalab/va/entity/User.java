package kz.javalab.va.entity;

import java.util.List;

public class User {
    private Integer id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private double balance;
    private Access access;
    private List<Order> orders;
}
