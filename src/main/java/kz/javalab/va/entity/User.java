package kz.javalab.va.entity;

import java.util.List;

public class User extends Entity {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private int balance;
    private Access access;
    private List<Order> orders;
}
