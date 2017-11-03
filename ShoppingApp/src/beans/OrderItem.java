package beans;

public class OrderItem {
  private Integer id;
  private Integer orderId;
  private Integer productId;
  private float unitPrice;
  private Integer quantity;
  
  public OrderItem() {}
  
  public Integer getId() { return id; }
  
  public void setId(Integer id) {
    this.id = id;
  }
  
  public Integer getOrderId() { return orderId; }
  
  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }
  
  public Integer getProductId() { return productId; }
  
  public void setProductId(Integer productId) {
    this.productId = productId;
  }
  
  public float getUnitPrice() { return unitPrice; }
  
  public void setUnitPrice(float unitPrice) {
    this.unitPrice = unitPrice;
  }
  
  public Integer getQuantity() { return quantity; }
  
  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }
}
