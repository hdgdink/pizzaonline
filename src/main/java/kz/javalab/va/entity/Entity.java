package kz.javalab.va.entity;

import kz.javalab.va.dao.Identified;

public abstract class Entity implements Identified {
    private Integer Id;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        this.Id = id;
    }
}
