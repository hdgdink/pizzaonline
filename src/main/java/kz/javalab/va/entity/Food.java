package kz.javalab.va.entity;

public class Food extends Entity {
    private String name;
    private String compos;
    private Integer price;
    private Type type;
    private int size;

    public enum Type {
        PIZZA, SALAD, SUB, BEVERAGE
    }

    public enum Size {
        BIG, MEDIUM, SMALL;
    }
}
