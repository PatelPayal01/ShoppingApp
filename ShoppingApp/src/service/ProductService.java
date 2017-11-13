package service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import Factory.Factory;
import beans.Cart;
import beans.ProductDescription;
import beans.ReplyMessage;
import dao.ProductDAO;

@Path("/products")
public class ProductService {
	
	@POST
	@Path("/addProductToCart")
	public ReplyMessage addProductToCart(Cart cart){
		ReplyMessage r = null;
		try {
			ProductDAO p = Factory.getProductDAO();
			r = p.addProductToCart(cart); 
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return r;
	}

}
