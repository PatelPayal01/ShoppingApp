package service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import Factory.Factory;
import beans.OrderData;
import beans.OrderItem;
import beans.ProductDescription;
import dao.CustomerDAO;

@Path("/customer")
public class CustomerService {
	
	@POST
	@Path("/orderitems")
	public List<OrderItem> getOrderitems(OrderData orderData){
		List<OrderItem> list = null;
		try{
			list = new ArrayList<>();
			CustomerDAO customerDAO = Factory.getCustomerDAO();
			list = customerDAO.getOrderItems(orderData);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return list ;
	}
	
	@POST
	@Path("/productDescription")
	public ProductDescription getProductDescription(OrderData orderData){
		ProductDescription product =null;
		try{
			CustomerDAO customerDAO = Factory.getCustomerDAO();
			product = customerDAO.getProductDescription(orderData);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return product;
	}
}
