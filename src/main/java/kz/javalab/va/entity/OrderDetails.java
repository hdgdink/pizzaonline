package kz.javalab.va.entity;


public class OrderDetails extends Entity {
    private Integer foodId;
    private String foodNameRu;
    private String foodNameEn;
    private String sizeName;
    private Integer typeId;
    private String typeName;
    private Integer quantity;
    private Integer orderId;
    private Integer price;


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getFinalPrice() {
        return price;
    }

    public void setFinalPrice(Integer finalPrice) {
        this.price = finalPrice;
    }

    public String getFoodNameRu() {
        return foodNameRu;
    }

    public void setFoodNameRu(String foodNameRu) {
        this.foodNameRu = foodNameRu;
    }

    public String getFoodNameEn() {
        return foodNameEn;
    }

    public void setFoodNameEn(String foodNameEn) {
        this.foodNameEn = foodNameEn;
    }
}
