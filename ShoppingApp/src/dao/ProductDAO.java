package dao;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
/*Using only hibernate*/
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
		CartEntity cartEntity = null;

		try {

			Connection connection = makeConnection();
			factory = ((AnnotationConfiguration) new AnnotationConfiguration().configure())
					.addAnnotatedClass(ProductEntity.class).addAnnotatedClass(CartEntity.class)
					.addAnnotatedClass(CustomersEntity.class).buildSessionFactory();

			Session session = factory.openSession();
			CustomersEntity customer = (CustomersEntity) session.get(CustomersEntity.class,
					cartContent.getCustomerId());

			String query = "select * from cart where customerId = " + customer.getId();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			// query.setInteger("id", cartContent.getCustomerId());

			Gson gson = new Gson();
			Type type = new TypeToken<List<Cart>>() {
			}.getType();
			// System.out.println("CONVERT TO STRING");
			//
			// System.out.println(gson.toJson(cartContent.getCart(), type));
			String json = gson.toJson(cartContent.getCart());
			System.out.println(json);

			if (resultSet.next()) {
				System.out.println(resultSet.getInt(1));
				cartEntity = (CartEntity) session.get(CartEntity.class, resultSet.getInt(1));
//				List<Cart> subcart = gson.fromJson(cartEntity.getCartContent(), type); // Cart
				cartEntity.setCartContent(gson.toJson(cartContent.getCart()));																				// in
																								// DB

			} else {
				System.out.println("IN PRODUCT DAO");
				cartEntity = new CartEntity();
				cartEntity.setCustomer(customer);
				cartEntity.setCartContent(gson.toJson(cartContent.getCart()));
			}

			/*
			 * Convert Java Object to JSON String TRy Using Higher version of
			 * GSON
			 */
			/* Convert JSON string to Java Object */
			// List<Task> fromJson = gson.fromJson(json, type);

			// cart.setCartContent(json);

			session.beginTransaction();
			Integer id = (Integer) session.save(cartEntity);
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
			e.printStackTrace();
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
			Connection connection = makeConnection();
			factory = ((AnnotationConfiguration) new AnnotationConfiguration().configure())
					.addAnnotatedClass(CartEntity.class).buildSessionFactory();
			/* Add product count in a list to be returned */
			list.add(new ProductDAO().productCountInCart(cart));

			Session session = factory.openSession();
			String query = "select * from cart where customerid = " + cart.get_customerId();

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			Gson gson = new Gson();
			Type type = new TypeToken<CartContent>() {
			}.getType();

			while (resultSet.next()) {
				System.out.println("CartFromDb");
				String json = resultSet.getString(3);
				CartContent cartContent = gson.fromJson(json, type);
				list.add(cartContent);
			}

			session.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
}

/*
 * START Converting to bytestream to store list in database column
 * ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutputStream
 * objOstream = new ObjectOutputStream(baos);
 * objOstream.writeObject(cartContent); byte[] bArray = baos.toByteArray(); END
 */
