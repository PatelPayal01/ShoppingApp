package dao;

/*Using only hibernate*/
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;

import beans.Cart;
import beans.ProductDescription;
import beans.ReplyMessage;
import entities.CartEntity;

public class ProductDAO {

	public ReplyMessage addProductToCart(Cart cart) {
		ReplyMessage r = null;

		try {

			Session session = new AnnotationConfiguration().configure().buildSessionFactory().openSession();
			ProductDescription productDescription = (ProductDescription) session.get(ProductDescription.class,
					cart.getProductId());

			CartEntity c = new CartEntity();
			c.setCustomerid(cart.getCustomerId());
			c.setProductid(cart.getProductId());
			c.setQuantity(cart.getQuantity());
			c.setUnitprice(productDescription.getUnitPrice());

			session.beginTransaction();
			Integer id = (Integer) session.save(c);
			session.getTransaction().commit();
			r= new ReplyMessage();
			session.close();
			if(id!=null){
				
				r.setMessage("Product added to cart");
			}
			else{
				r.setMessage("Some error occured");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return r;
	}
}
