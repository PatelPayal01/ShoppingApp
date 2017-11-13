package entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Cart")
public class CartEntity {
	
//	
//	id int ,
//	customerid int not null,
//	productid int not null,
//	unitprice decimal(12,2) not null,
//	quantity int not null,
	
	private int id;
	private int customerid;
	private int productid;
	private float unitprice;
	private int quantity;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public float getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(float unitprice) {
		this.unitprice = unitprice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

}
