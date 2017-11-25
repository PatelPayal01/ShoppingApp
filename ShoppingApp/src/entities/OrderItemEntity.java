package entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="OrderItem")
public class OrderItemEntity {

	@Id
	@GenericGenerator(name = "kaugen", strategy = "increment")
	@GeneratedValue(generator = "kaugen")
	private Integer id;
	private float unitPrice;
	private Integer quantity;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "orderid")
	private OrderEntity orderEntity;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "productid")
	private ProductEntity product;
	
	public OrderEntity getOrderEntity() {
		return orderEntity;
	}

	public void setOrderEntity(OrderEntity orderEntity) {
		this.orderEntity = orderEntity;
	}

	public ProductEntity getProductEntity() {
		return productEntity;
	}

	public void setProductEntity(ProductEntity productEntity) {
		this.productEntity = productEntity;
	}

	@OneToOne(cascade = CascadeType.ALL)
	private ProductEntity productEntity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
