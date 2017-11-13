package dao;

import beans.CustomerData;
import beans.Customers;
import beans.Login;
import beans.OrderData;
import beans.Orders;
import beans.ProductDesciption;
import entities.CustomersEntity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

public class HelloDAO {
	public HelloDAO() {
	}

	public static Connection makeConnection()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		String username = "testuser";
		String psswd = "123";
		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", username, psswd);

		return connect;
	}

	public List<Customers> getCustomerfromResultSet(ResultSet resultSet) {
		List<Customers> list = null;

		try {
			list = new ArrayList();
			while (resultSet.next()) {
				Customers customers = new Customers();
				customers.setId(Integer.valueOf(resultSet.getInt("id")));
				customers.setFirstName(resultSet.getString("firstname"));
				customers.setLastName(resultSet.getString("lastname"));
				customers.setCity(resultSet.getString("city"));
				customers.setPhone(resultSet.getString("phone"));
				customers.setCountry(resultSet.getString("country"));

				list.add(customers);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<ProductDesciption> getProductfromResultSet(ResultSet resultSet) {
		List<ProductDesciption> list = null;

		try {
			list = new ArrayList();
			while (resultSet.next()) {
				ProductDesciption productDesciption = new ProductDesciption(resultSet.getInt(1), resultSet.getString(2),
						resultSet.getInt(3), resultSet.getFloat(4), resultSet.getString(5));
				list.add(productDesciption);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Object> getCustomerData(CustomerData customerData) {
		List<Object> list = new ArrayList<>();
		List<Customers> customerList = null;
		List<ProductDesciption> pList = null;
		try {
			Connection connection = makeConnection();
			String custOrProd = customerData.getCustOrProd();
			Integer startIndex = customerData.getStartIndex();
			Integer endIndex = customerData.getEndIndex();
			String sortby = "";
			
			if (customerData.getCustOrProd().toLowerCase() == "customer") {
				sortby = customerData.getSortByForCustomer();
			} else {
				sortby = customerData.getSortByForProduct();
			}

			list.add(dataCount(custOrProd));

			String query = "select * from (select a.*, rownum rnum from (select * from " + custOrProd
					+ " order by "+sortby+" ) a where rownum <= ?)  where rnum >= ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setString(1, sortby);
			preparedStatement.setInt(1, endIndex);
			preparedStatement.setInt(2, startIndex);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (customerData.getCustOrProd().toLowerCase() == "customer") {
				customerList = new ArrayList<>();
				customerList = new HelloDAO().getCustomerfromResultSet(resultSet);
				list.add(customerList);
			} else {

				pList = new ArrayList<>();
				pList = new HelloDAO().getProductfromResultSet(resultSet);
				list.add(pList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Integer dataCount(String CustOrProd) {
		Integer rowCount = Integer.valueOf(0);
		try {
			Connection connect = makeConnection();

			PreparedStatement statement = connect.prepareStatement("SELECT count(*) from " + CustOrProd);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			rowCount = Integer.valueOf(resultSet.getInt(1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount;
	}

	public Customers login(Login login) {
		boolean isLogin = false;
		Customers customers = null;
		Integer customerId = Integer.valueOf(0);
		try {
			Connection connect = makeConnection();

			String query1 = "select * from customer where username = ? and  password = ?";

			PreparedStatement statement1 = connect.prepareStatement(query1);
			statement1.setString(1, login.getUsername());
			statement1.setString(2, login.getPassword());

			ResultSet resultSet1 = statement1.executeQuery();
			if (resultSet1.next()) {
				isLogin = true;
				customerId = Integer.valueOf(resultSet1.getInt(1));
				String query2 = "select * from customer where id = " + customerId;
				PreparedStatement statement2 = connect.prepareStatement(query2);

				ResultSet resultSet2 = statement2.executeQuery(query2);

				if (resultSet2.next()) {
					customers = new Customers();
					customers.setId(customerId);
					customers.setFirstName(resultSet2.getString(2));
					customers.setLastName(resultSet2.getString(3));
					customers.setCity(resultSet2.getString(4));
					customers.setCountry(resultSet2.getString(5));
					customers.setPhone(resultSet2.getString(6));
				} else {
					customers = new Customers();
					customers.setMessage("Invalid Username or Password");
				}
			} else {
				customers = new Customers();
				customers.setMessage("Invalid Username or Password");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customers;
	}

	public List<Orders> getOrdersofCustomer(OrderData orderData) {
		List<Orders> orders = null;
		try {
			Connection connect = makeConnection();
			String query = "select * from orders where customerid = " + orderData.getCustomerId();

			PreparedStatement statement = connect.prepareStatement(query);

			orders = new ArrayList();
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Orders orders2 = new Orders();
				orders2.setId(Integer.valueOf(resultSet.getInt(1)));
				orders2.setOrderDate(resultSet.getDate(2));
				orders2.setCustomerId(Integer.valueOf(resultSet.getInt(3)));
				orders2.setTotalAmount(resultSet.getFloat(4));
				orders2.setOrderNumber(resultSet.getString(5));

				orders.add(orders2);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return orders;
	}

	public static void storeData() {
		CustomersEntity customerEntity = new CustomersEntity();
		customerEntity.setId(Integer.valueOf(1000));
		customerEntity.setFirstName("Payal");
		customerEntity.setLastName("Patel");
		customerEntity.setCity("Mumbai");
		customerEntity.setPhone("7738798954");
		customerEntity.setCountry("India");

		SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(customerEntity);
		session.getTransaction().commit();
		session.close();
	}
}
