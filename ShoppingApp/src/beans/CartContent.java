package beans;

import java.io.Serializable;
import java.util.ArrayList;

public class CartContent implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<Cart> cart;
	private Integer customerId;
	private Integer quantity;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public ArrayList<Cart> getCart() {
		return cart;
	}

	public void setCart(ArrayList<Cart> cart) {
		this.cart = cart;
	}

}
