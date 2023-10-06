package in.fssa.tharasworld.dao;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import in.fssa.tharasworld.entity.AddressEntity;
import in.fssa.tharasworld.entity.OrderEntity;
import in.fssa.tharasworld.exception.PersistenceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.model.OrderStatus;
import in.fssa.tharasworld.util.ConnectionUtil;
import in.fssa.tharasworld.util.Logger;

public class OrderDAO {
	
	public void create(OrderEntity newOrder) throws PersistenceException {

		Connection connection = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO orders (phone_number, status, quantity, payment, user_id, address_id, price_id, seller_id) "
					+ "VALUES (?, 'WAITING_LIST', ?, 'Cash_on_delivery', ?, ?, ?, ?)";
			connection = ConnectionUtil.getConnection();
			ps = connection.prepareStatement(query);
			
			ps.setLong(1, newOrder.getPhoneNumber());
			ps.setInt(2, newOrder.getQuantity());
			ps.setInt(3, newOrder.getUserId());
			ps.setInt(4, newOrder.getAddressId());
			ps.setInt(5, newOrder.getPriceId());
			ps.setInt(6, newOrder.getSellerId());

			ps.executeUpdate();

			Logger.info("Order has been created successfully");

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(connection, ps);
		}

	}
	
	public void acceptOrder(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			
			 LocalDate todayDate = LocalDate.now(); 
		        int daysToAdd = 3; 
		        LocalDate deliveryDate = todayDate.plusDays(daysToAdd);
			
			String query = "UPDATE orders SET status='ON_THE_WAY', delivery_date=? WHERE is_active=1 AND order_id=?";

			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setDate(1, Date.valueOf(deliveryDate));
			ps.setInt(2, id);

			ps.executeUpdate();

			Logger.info("Order has been delivered within 3 days");

		} catch (SQLException e) {

			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps);

		}

	}
	
	public void cancelOrder(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query = "UPDATE orders SET status = 'CANCELLED', is_active=0 WHERE is_active=1 AND order_id=?";

			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, id);

			ps.executeUpdate();

			Logger.info("Order has been cancelled successfully");

		} catch (SQLException e) {

			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps);

		}

	}
	
	public void orderDelivered(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query = "UPDATE orders SET status = 'DELIVERED', is_active=0 WHERE is_active=1 AND order_id=?";

			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, id);

			ps.executeUpdate();

			Logger.info("Order has been delivered successfully");

		} catch (SQLException e) {

			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps);

		}

	}
	
	public OrderEntity findByOrderId(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		OrderEntity order = null;

		try {

			String query = "SELECT order_id, ordered_date, phone_number, is_active, quantity, status, user_id, address_id, price_id  FROM orders WHERE order_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				
				order = new OrderEntity();

				order.setOrderId(rs.getInt("order_id"));
				
				java.sql.Date orderedDateSQL = rs.getDate("ordered_date");
				
				order.setOrderedDate(orderedDateSQL.toLocalDate());
				
				order.setPhoneNumber(rs.getLong("phone_number"));
				order.setActive(rs.getBoolean("is_active"));
				order.setQuantity(rs.getInt("quantity"));
				
				String status = rs.getString("status");
				OrderStatus orderstatus = OrderStatus.valueOf(status);
				
				order.setOrderStatus(orderstatus);
				order.setUserId(rs.getInt("user_id"));
				order.setAddressId(rs.getInt("address_id"));
				order.setPriceId(rs.getInt("price_id"));

			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}

		return order;

	}
	
	public int findAddressidByOrderId(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int addressId = 0;
		
		OrderEntity order = null;

		try {

			String query = "SELECT order_id, ordered_date, phone_number, is_active, quantity, status, user_id, address_id, price_id  FROM orders WHERE order_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				
				order = new OrderEntity();

				order.setOrderId(rs.getInt("order_id"));
				
				java.sql.Date orderedDateSQL = rs.getDate("ordered_date");
				
				order.setOrderedDate(orderedDateSQL.toLocalDate());
				
				order.setPhoneNumber(rs.getLong("phone_number"));
				order.setActive(rs.getBoolean("is_active"));
				order.setQuantity(rs.getInt("quantity"));
				
				String status = rs.getString("status");
				OrderStatus orderstatus = OrderStatus.valueOf(status);
				
				order.setOrderStatus(orderstatus);
				order.setUserId(rs.getInt("user_id"));
				order.setAddressId(rs.getInt("address_id"));
				order.setPriceId(rs.getInt("price_id"));

			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}

		return addressId = order.getAddressId();

	}
	
	public List<OrderEntity> findOrdersByUserId(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OrderEntity> orders = new ArrayList<>();

		try {

			String query = "SELECT order_id, ordered_date, phone_number, is_active, quantity, status, user_id, address_id, price_id  FROM orders WHERE user_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				
				OrderEntity order = new OrderEntity();

				order.setOrderId(rs.getInt("order_id"));
				
				java.sql.Date orderedDateSQL = rs.getDate("ordered_date");
				
				order.setOrderedDate(orderedDateSQL.toLocalDate());
				
				order.setPhoneNumber(rs.getLong("phone_number"));
				order.setActive(rs.getBoolean("is_active"));
				order.setQuantity(rs.getInt("quantity"));
				
				String status = rs.getString("status");
				OrderStatus orderstatus = OrderStatus.valueOf(status);
				
				order.setOrderStatus(orderstatus);
				order.setUserId(rs.getInt("user_id"));
				order.setAddressId(rs.getInt("address_id"));
				order.setPriceId(rs.getInt("price_id"));
				
				orders.add(order);

			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}

		return orders;

	}

	
	public List<OrderEntity> findOrdersBySellerId(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OrderEntity> orders = new ArrayList<>();

		try {

			String query = "SELECT order_id, ordered_date, phone_number, is_active, quantity, status, user_id, address_id, price_id  FROM orders WHERE seller_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				
				OrderEntity order = new OrderEntity();

				order.setOrderId(rs.getInt("order_id"));
				
				java.sql.Date orderedDateSQL = rs.getDate("ordered_date");
				
				order.setOrderedDate(orderedDateSQL.toLocalDate());
				
				order.setPhoneNumber(rs.getLong("phone_number"));
				order.setActive(rs.getBoolean("is_active"));
				order.setQuantity(rs.getInt("quantity"));
				
				String status = rs.getString("status");
				OrderStatus orderstatus = OrderStatus.valueOf(status);
				
				order.setOrderStatus(orderstatus);
				order.setUserId(rs.getInt("user_id"));
				order.setAddressId(rs.getInt("address_id"));
				order.setPriceId(rs.getInt("price_id"));
				
				orders.add(order);

			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}

		return orders;

	}
	
	public void checkorderExistWithOrderId(int id) throws PersistenceException, ValidationException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		AddressEntity user = null;

		try {

			String query = "SELECT order_id, ordered_date, phone_number, is_active, quantity, status, user_id, address_id, price_id FROM orders WHERE order_id=?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (!rs.next()) {
				throw new ValidationException("Order does not exists");
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}

	}
	
}
