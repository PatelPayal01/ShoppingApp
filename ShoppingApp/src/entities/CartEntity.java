package entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Cart")
public class CartEntity {


	@Id
	@GenericGenerator(name = "kaugen", strategy = "increment")
	@GeneratedValue(generator = "kaugen")
	private int id;
	private float unitprice;
	private int quantity;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customerid")
	private CustomersEntity customer;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "productid")
	private ProductEntity product;
	
	public CustomersEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomersEntity customer) {
		this.customer = customer;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
