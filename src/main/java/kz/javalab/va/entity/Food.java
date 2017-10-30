package kz.javalab.va.entity;

import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.TypeDao;
import org.apache.log4j.Logger;

import java.io.Serializable;

public class Food extends Entity implements Serializable {
    private static final Logger LOGGER = Logger.getLogger(Food.class);
    private Integer typeId;
    private String nameEn;
    private String nameRu;
    private String discriptionEn;
    private String discriptionRu;
    private Integer price;
    private Type type;
    private String img;
    private Boolean active;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getDiscriptionEn() {
        return discriptionEn;
    }

    public void setDiscriptionEn(String discriptionEn) {
        this.discriptionEn = discriptionEn;
    }

    public String getDiscriptionRu() {
        return discriptionRu;
    }

    public void setDiscriptionRu(String discriptionRu) {
        this.discriptionRu = discriptionRu;
    }


    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Type getType() throws ConnectionPoolException {
        try {
            TypeDao typeDao = new TypeDao();
            type = typeDao.getById(typeId);
        } catch (DAOException e) {
            LOGGER.error("Error of initialization TypeDao", e);
            throw new RuntimeException("Error getting Type of product from database", e);
        }
        return type;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
