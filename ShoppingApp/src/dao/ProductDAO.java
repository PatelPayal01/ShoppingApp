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
import beans.ProductDescription;
import beans.ReplyMessage;
import beans.SearchProduct;
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

			if (resultSet.next()) {
				cartEntity = (CartEntity) session.get(CartEntity.class, resultSet.getInt(1));
				cartEntity.setCartContent(cartJsonString);
				r = new ReplyMessage();
				r.setMessage("Cart Updated");
			} else {
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

	public List<ProductDescription> searchProducts(SearchProduct searchProduct) {
		List<ProductDescription> list = new ArrayList<>();
		final SessionFactory factory;
		try {

			Connection connection = makeConnection();
			factory = ((AnnotationConfiguration) new AnnotationConfiguration().configure())
					.addAnnotatedClass(ProductEntity.class).buildSessionFactory();
			String sortBy = searchProduct.getPaginatedata().getSortByForProduct();
			
			String whereClause="";
			if (searchProduct.getSearchText() != null)
				if (searchProduct.getSearchText().trim().length() > 0)
					whereClause = "where lower(productname) " + "like '%" + searchProduct.getSearchText().toLowerCase() + "%'";
			
//			String query = "select * from (select a.*, rownum rnum from (select * from product where lower(productname) "
//					+ "like '%"+searchProduct.getSearchText().toLowerCase()+"%'  order by "+sortBy+" ) a where rownum <= ?)  where rnum >= ?";
			String query = "select * from (select a.*, rownum rnum from (select * from product " + whereClause
					+ "  order by " + sortBy + " ) a where rownum <= ?)  where rnum >= ?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setString(1,searchProduct.getSearchText());
			preparedStatement.setInt(1, searchProduct.getPaginatedata().getEndIndex());
			preparedStatement.setInt(2, searchProduct.getPaginatedata().getStartIndex());

			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){ 	
				ProductDescription p = new ProductDescription(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getFloat(4),resultSet.getString(5));
				list.add(p);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
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
