package beans;

import java.io.Serializable;
import java.util.List;

public class CartContent implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Cart> cart;

	public List<Cart> getCart() {
		return cart;
	}

	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}

}
