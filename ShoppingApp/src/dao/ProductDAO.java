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

			Gson gson = new Gson();
			Type type = new TypeToken<List<Cart>>() {
			}.getType();
			String cartJsonString = gson.toJson(cartContent.getCart());
			
			session.beginTransaction();
			
			if(resultSet.next()){
			cartEntity = (CartEntity) session.get(CartEntity.class, resultSet.getInt(1));
			cartEntity.setCartContent(cartJsonString);
			r = new ReplyMessage();
			r.setMessage("Quantity Updated as product was already in cart ");
			}
			else{
				cartEntity = new CartEntity();
				cartEntity.setCustomer(customer);
				cartEntity.setCartContent(cartJsonString);
				Integer id = (Integer) session.save(cartEntity);
				r = new ReplyMessage();
				r.setMessage("Product added to cart");
			}

			
			
			session.getTransaction().commit();
			session.close();
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

	public List<Cart> getProductsInCart(Cart cart) {
		List<Cart> cartlist = null;
		final SessionFactory factory;
		try {
			Connection connection = makeConnection();
			factory = ((AnnotationConfiguration) new AnnotationConfiguration().configure())
					.addAnnotatedClass(CartEntity.class).buildSessionFactory();
			/* Add product count in a list to be returned */

			Session session = factory.openSession();
			String query = "select * from cart where customerid = " + cart.get_customerId();

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			Gson gson = new Gson();
			Type type = new TypeToken<List<Cart>>() {
			}.getType();

			while (resultSet.next()) {
				String json = resultSet.getString(3);
				cartlist = new ArrayList<>();
				cartlist = gson.fromJson(json, type);

			}

			session.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return cartlist;
	}
}

/*
 * START Converting to bytestream to store list in database column
 * ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutputStream
 * objOstream = new ObjectOutputStream(baos);
 * objOstream.writeObject(cartContent); byte[] bArray = baos.toByteArray(); END
 */
