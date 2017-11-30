package dao;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/*Using only hibernate*/
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.Query;

import beans.Cart;
import beans.CartContent;
import beans.ReplyMessage;
import entities.CartEntity;
import entities.CustomersEntity;
import entities.ProductEntity;

public class ProductDAO {

//	public ReplyMessage addProductToCart(Cart cart) {
//		ReplyMessage r = null;
//		final SessionFactory factory;
//
//		try {
//
//			factory = ((AnnotationConfiguration) new AnnotationConfiguration().configure())
//					.addAnnotatedClass(ProductEntity.class).addAnnotatedClass(CartEntity.class)
//					.addAnnotatedClass(CustomersEntity.class).buildSessionFactory();
//
//			Session session = factory.openSession();
//			ProductEntity productEntity = (ProductEntity) session.get(ProductEntity.class, cart.getProductId());
//			CustomersEntity customer = (CustomersEntity) session.get(CustomersEntity.class,cart.getCustomerId());
//			CartEntity c = new CartEntity();
//			c.setCustomer(customer); 
//			c.setProduct(productEntity);
//			c.setUnitprice(productEntity.getUnitPrice());
//
//			session.beginTransaction();
//			Integer id = (Integer) session.save(c);
//			session.getTransaction().commit();
//			r = new ReplyMessage();
//			session.close();
//			if (id != null) {
//
//				r.setMessage("Product added to cart");
//			} else {
//				r.setMessage("Some error occured");
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			// TODO: handle exception
//		}
//
//		return r;
//	}
	
	public ReplyMessage addProductToCart(CartContent cartContent) {
		ReplyMessage r = null;
		final SessionFactory factory;

		try {
			System.out.println(cartContent.getCart()+"CAAAAAAAARTTTTTTTTTTTTTT");

			factory = ((AnnotationConfiguration) new AnnotationConfiguration().configure())
					.addAnnotatedClass(ProductEntity.class).addAnnotatedClass(CartEntity.class)
					.addAnnotatedClass(CustomersEntity.class).buildSessionFactory();
//
			Session session = factory.openSession();
//			ProductEntity productEntity = (ProductEntity) session.get(ProductEntity.class, cart.getProductId());
			CustomersEntity customer = (CustomersEntity) session.get(CustomersEntity.class,cartContent.getCart().get(0).get_customerId());
			CartEntity c = new CartEntity();
			c.setCustomer(customer); 

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream objOstream = new ObjectOutputStream(baos);
            objOstream.writeObject(cartContent);
            byte[] bArray = baos.toByteArray();
			
			c.setCartContent(bArray);
//
			session.beginTransaction();
			Integer id = (Integer) session.save(c);
			session.getTransaction().commit();
			r = new ReplyMessage();
			session.close();
			if (id != null) {

				r.setMessage("Product added to cart");
			}else{
				r.setMessage("Some error occured");
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return r;
	}

	public Integer productCountInCart(Cart cart) {
		Integer count = 0;
		final SessionFactory factory;
		try {
			factory = ((AnnotationConfiguration) new AnnotationConfiguration().configure())
					.addAnnotatedClass(CartEntity.class).buildSessionFactory();
			
			Session session = factory.openSession();
			String hql = "select count(*) from cart where customerid = "+cart.get_customerId();
			
			Query query = session.createQuery(hql);
			
			count = ((Number) query.uniqueResult()).intValue();
			
			session.close();
			

		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}
	public List<Object> productsInCart(Cart cart) {
		List<Object> list = new ArrayList<>();
		final SessionFactory factory;
		try {
			factory = ((AnnotationConfiguration) new AnnotationConfiguration().configure())
					.addAnnotatedClass(CartEntity.class).buildSessionFactory();
			/* Add product count in a list to be returned*/
			list.add(new ProductDAO().productCountInCart(cart));
			
			Session session = factory.openSession();
			String hql = "select * from cart where customerid = "+cart.get_customerId();
			
			
			//exceute query
			
			session.close();
			

		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
}
