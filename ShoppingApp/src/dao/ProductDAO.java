package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

/*Using only hibernate*/
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import beans.Cart;
import beans.ReplyMessage;
import entities.CartEntity;
import entities.CustomersEntity;
import entities.ProductEntity;

public class ProductDAO {

	public ReplyMessage addProductToCart(Cart cart) {
		ReplyMessage r = null;
		final SessionFactory factory;

		try {
			
			factory = ((AnnotationConfiguration) new AnnotationConfiguration().
	                configure()).addAnnotatedClass(ProductEntity.class)
					.addAnnotatedClass(CartEntity.class)
					.addAnnotatedClass(CustomersEntity.class).
	                buildSessionFactory();
			
			Session session = factory.openSession();
			ProductEntity productEntity = (ProductEntity) session.get(ProductEntity.class,new Integer(1));

			CartEntity c = new CartEntity();
			c.setCustomerid(cart.getCustomerId());
			c.setProductid(cart.getProductId());
			c.setQuantity(cart.getQuantity());
			c.setUnitprice(productEntity.getUnitPrice());

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
			e.printStackTrace();
			// TODO: handle exception
		}

		return r;
	}
}
