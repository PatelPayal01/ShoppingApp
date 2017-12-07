package dao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import connection.DBConnection;
import entities.CartEntity;
import entities.CustomersEntity;
import entities.ProductEntity;

public class ProductDAO extends DBConnection {

	public ReplyMessage addProductToCart(CartContent cartContent) {
		ReplyMessage r = null;
		final SessionFactory factory;

		try {

			factory = ((AnnotationConfiguration) new AnnotationConfiguration().configure())
					.addAnnotatedClass(ProductEntity.class).addAnnotatedClass(CartEntity.class)
					.addAnnotatedClass(CustomersEntity.class).buildSessionFactory();

			Session session = factory.openSession();
			CustomersEntity customer = (CustomersEntity) session.get(CustomersEntity.class,
					cartContent.getCustomerId());
			CartEntity c = new CartEntity();
			c.setCustomer(customer);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream objOstream = new ObjectOutputStream(baos);
			objOstream.writeObject(cartContent);
			byte[] bArray = baos.toByteArray();

			c.setCartContent(bArray);

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
			System.out.println(e.getMessage());
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
			String hql = "select count(*) from cart where customerid = " + cart.get_customerId();

			Query query = session.createQuery(hql);

			count = ((Number) query.uniqueResult()).intValue();

			session.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}

	public List<Object> getProductsInCart(Cart cart) {
		List<Object> list = new ArrayList<>();
		final SessionFactory factory;
		try {
			Connection connection =makeConnection();
			System.out.println("GEEETT CONTENTS");
			factory = ((AnnotationConfiguration) new AnnotationConfiguration().configure())
					.addAnnotatedClass(CartEntity.class).buildSessionFactory();
			/* Add product count in a list to be returned */
			list.add(new ProductDAO().productCountInCart(cart));

			Session session = factory.openSession();
			String query = "select * from cart where customerid = " + cart.get_customerId();

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				System.out.println("CartFromDb");
				byte[] buf = resultSet.getBytes(3);
				ObjectInputStream objectInputStream = null;
				if (buf != null)
					System.out.println("FRRROOMMM   DDDBB");
					objectInputStream = new ObjectInputStream(new ByteArrayInputStream(buf));
				Object deSerializedObject = objectInputStream.readObject();
				

			}

			session.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
}
