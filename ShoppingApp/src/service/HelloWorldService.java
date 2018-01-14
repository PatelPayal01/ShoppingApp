package service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import Factory.Factory;
import beans.CustomerData;
import beans.Customers;
import beans.Login;
import beans.OrderData;
import beans.Orders;
import dao.HelloDAO;

@Path("/hello")
public class HelloWorldService {

	@POST
	@Path("/paginateData")
	public List<Object> paginatedData(CustomerData customerData) {
		List<Object> list = null;
		try {
			System.out.println("PAAAAAAAAAAAAAAAA");
			HelloDAO helloDAO = Factory.getHelloDAO();
			list = new ArrayList<Object>();
			list = helloDAO.getCustomerData(customerData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@POST
	@Path("/login")
	public Customers login(Login login) {
		Customers customers = null;
		try {
			HelloDAO helloDAO = Factory.getHelloDAO();
			customers = helloDAO.login(login);
			// System.out.println(customers.getFirstName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customers;
	}

	@POST
	@Path("/ordersOfCustomer")
	public List<Orders> orderOfCustomer(OrderData orderData) {
		List<Orders> orders = null;
		try {

			HelloDAO helloDAO = Factory.getHelloDAO();

			orders = helloDAO.getOrdersofCustomer(orderData);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return orders;

	}

}