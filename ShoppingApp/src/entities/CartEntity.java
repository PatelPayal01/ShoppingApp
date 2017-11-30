package entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.sun.mail.iap.ByteArray;

@Entity
@Table(name = "Cart")
public class CartEntity {

	@Id
	@GenericGenerator(name = "kaugen", strategy = "increment")
	@GeneratedValue(generator = "kaugen")
	private int id;
	private byte[] cartContent;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customerid")
	private CustomersEntity customer;

	public CustomersEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomersEntity customer) {
		this.customer = customer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getCartContent() {
		return cartContent;
	}

	public void setCartContent(byte[] cartContent) {
		this.cartContent = cartContent;
	}

}
