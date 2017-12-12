package service;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import Factory.Factory;
import beans.Cart;
import beans.CartContent;
import beans.ReplyMessage;
import dao.ProductDAO;

@Path("/products")
public class ProductService {

	@POST
	@Path("/addProductToCart")
	public ReplyMessage addProductToCart(CartContent cart) {
		ReplyMessage r = null;
		try {
			ProductDAO p = Factory.getProductDAO();
			r = p.addProductToCart(cart);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return r;
	}

	@POST
	@Path("/getCartContent")
	public List<Cart> productsInCart(Cart cart) {
		List<Cart> cartContent = null;
		try {
			System.out.println("IN PRODUCT DAO");
			ProductDAO p = Factory.getProductDAO();
			cartContent = p.getProductsInCart(cart);
			System.out.println("cart size"+cartContent);
		}

		catch (Exception e) {
			// TODO: handle exception
		}
		return cartContent;
	}
}
