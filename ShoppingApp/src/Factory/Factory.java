package Factory;

import dao.CustomerDAO;
import service.CustomerService;

public class Factory {
	public Factory() {
	}

	public static dao.ProductDAO getProductDAO() {
		return new dao.ProductDAO();
	}
	
	public static service.ProductService getProductService() {
		return new service.ProductService();
	}
	
	public static dao.HelloDAO getHelloDAO() {
		return new dao.HelloDAO();
	}
	public static service.HelloWorldService getHelloWorldService() {
		return new service.HelloWorldService();
	}

	public static service.SignUp_LoginService getSignUp_LoginService() {
		return new service.SignUp_LoginService();
	}

	public static dao.SignUp_LoginDAO getSignUp_LoginDAO() {
		return new dao.SignUp_LoginDAO();
	}

	public static CustomerDAO getCustomerDAO() {
		return new CustomerDAO();
	}

	public static CustomerService getCustomerService() {
		return new CustomerService();
	}
}
