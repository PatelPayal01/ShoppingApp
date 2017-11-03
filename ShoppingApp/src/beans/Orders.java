package beans;

import java.util.Date;

public class Orders {
  private Integer id;
  private Integer customerId;
  private float totalAmount;
  
  public Orders() {}
  
  public String getMessage() {
    return message;
  }
  
  public void setMessage(String message) { this.message = message; }
  
  private String orderNumber;
  public Integer getId() { return id; }
  
  public void setId(Integer id) {
    this.id = id;
  }
  
  public Integer getCustomerId() { return customerId; }
  
  public void setCustomerId(Integer customerId) {
    this.customerId = customerId;
  }
  
  public float getTotalAmount() { return totalAmount; }
  
  public void setTotalAmount(float totalAmount) {
    this.totalAmount = totalAmount;
  }
  
  public String getOrderNumber() { return orderNumber; }
  

  public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }
  private Date orderDate;
  
  public Date getOrderDate() { return orderDate; }
  
  private String message;
  public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }
}
