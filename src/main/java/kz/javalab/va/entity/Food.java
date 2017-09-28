package kz.javalab.va.entity;

public class Food extends Entity {
    private Integer typeId;
    private String nameEn;
    private String nameRu;
    private String discriptionEn;
    private String discriptionRu;
    private Integer sizeId;
    private Integer price;
    private Type type;
    private Size size;
    private String img;


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

    public Integer getSizeId() {
        return sizeId;
    }

    public void setSizeId(Integer sizeId) {
        this.sizeId = sizeId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Type getType() {
        return type;
    }


    public Size getSize() {
        return size;
    }


}
