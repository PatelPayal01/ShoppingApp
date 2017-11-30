package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;


/*Using only hibernate*/
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.Query;

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

			factory = ((AnnotationConfiguration) new AnnotationConfiguration().configure())
					.addAnnotatedClass(ProductEntity.class).addAnnotatedClass(CartEntity.class)
					.addAnnotatedClass(CustomersEntity.class).buildSessionFactory();

			Session session = factory.openSession();
			ProductEntity productEntity = (ProductEntity) session.get(ProductEntity.class, cart.getProductId());
			CustomersEntity customer = (CustomersEntity) session.get(CustomersEntity.class,cart.getCustomerId());
System.out.println(productEntity.toString());
			CartEntity c = new CartEntity();
			c.setCustomer(customer); 
			c.setProduct(productEntity);
			c.setUnitprice(productEntity.getUnitPrice());

			session.beginTransaction();
			Integer id = (Integer) session.save(c);
			session.getTransaction().commit();
			r = new ReplyMessage();
			session.close();
			if (id != null) {

				r.setMessage("Product added to cart");
			} else {
				r.setMessage("Some error occured");
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return r;
	}

	public Integer productsInCart(Cart cart) {
		Integer count = 0;
		final SessionFactory factory;
		try {
			factory = ((AnnotationConfiguration) new AnnotationConfiguration().configure())
					.addAnnotatedClass(CartEntity.class).buildSessionFactory();
			
			Session session = factory.openSession();
			String hql = "select count(*) from cart where customerid = "+cart.getCustomerId();
			
			Query query = session.createQuery(hql);
			
			count = ((Number) query.uniqueResult()).intValue();
			
			session.close();
			

		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}
}
