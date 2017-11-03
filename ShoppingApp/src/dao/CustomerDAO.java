package dao;

import beans.OrderData;
import beans.OrderItem;
import beans.ProductDesciption;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
	public CustomerDAO() {
	}

	public ProductDesciption getProductDescription(OrderData orderData) {
		ProductDesciption product = null;
		try {
			Connection connection = HelloDAO.makeConnection();

			String query = "select * from product where id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, orderData.getProductId());

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				product = new ProductDesciption(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
													resultSet.getFloat(4), resultSet.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return product;
	}

	public List<OrderItem> getOrderItems(OrderData orderData) {
		List<OrderItem> list = null;
		try {
			Connection connection = HelloDAO.makeConnection();

			String query = "select * from orderitem where orderid = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, orderData.getOrderId().intValue());

			ResultSet resultSet = preparedStatement.executeQuery();
			list = new ArrayList();
			while (resultSet.next()) {
				OrderItem orderItem = new OrderItem();

				orderItem.setId(Integer.valueOf(resultSet.getInt(1)));
				orderItem.setOrderId(Integer.valueOf(resultSet.getInt(2)));
				orderItem.setProductId(Integer.valueOf(resultSet.getInt(3)));
				orderItem.setUnitPrice(resultSet.getFloat(4));
				orderItem.setQuantity(Integer.valueOf(resultSet.getInt(5)));

				list.add(orderItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
