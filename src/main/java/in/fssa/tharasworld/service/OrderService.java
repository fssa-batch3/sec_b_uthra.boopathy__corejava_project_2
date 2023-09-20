package in.fssa.tharasworld.service;


import java.util.*;

import in.fssa.tharasworld.dao.OrderDAO;
import in.fssa.tharasworld.entity.OrderEntity;
import in.fssa.tharasworld.exception.PersistenceException;
import in.fssa.tharasworld.exception.ServiceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.validator.OrderValidator;
import in.fssa.tharasworld.validator.ProductValidator;
import in.fssa.tharasworld.validator.UserValidator;

public class OrderService {

	
	public void create(OrderEntity newOrder) throws ServiceException, ValidationException {

		try {
			OrderValidator.validate(newOrder);

			OrderDAO orderDAO = new OrderDAO();
			orderDAO.create(newOrder);
		
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
	
	
	public static void acceptOrder(int id) throws ServiceException, ValidationException {

		try {

			OrderValidator.validateOrderId(id);

			OrderDAO orderDAO = new OrderDAO();
			orderDAO.acceptOrder(id);

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
	
	
	public static void cancelOrder(int id) throws ServiceException, ValidationException {

		try {

			OrderValidator.validateOrderId(id);

			OrderDAO orderDAO = new OrderDAO();
			orderDAO.cancelOrder(id);

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
	
	public static void orderDelivered(int id) throws ServiceException, ValidationException {

		try {

			OrderValidator.validateOrderId(id);

			OrderDAO orderDAO = new OrderDAO();
			orderDAO.orderDelivered(id);

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
	
	public static OrderEntity findOrderByOrderId(int id) throws ServiceException, ValidationException {

		OrderEntity order = null;

		try {

			OrderValidator.validateOrderId(id);

			OrderDAO orderDAO = new OrderDAO();
			order = orderDAO.findByOrderId(id);

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		return order;

	}
	
	public static int findAddressByOrderId(int id) throws ValidationException, ServiceException {
		
		int addressId = 0;
		
		try {

			OrderValidator.validateOrderId(id);

			OrderDAO orderDAO = new OrderDAO();
			addressId = orderDAO.findAddressidByOrderId(id);

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		return addressId;

	}
	
	
	
	public static List<OrderEntity> findOrdersByUserId(int id) throws ServiceException, ValidationException {

		List<OrderEntity> orderList = new ArrayList<>();

		try {

			UserValidator.CheckUserExistsWithId(id);

			OrderDAO orderDAO = new OrderDAO();
			orderList = orderDAO.findOrdersByUserId(id);

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		return orderList;

	}
	
	
	public static List<OrderEntity> findOrdersBySellerId(int id) throws ServiceException, ValidationException {

		List<OrderEntity> orderList = null;

		try {

			ProductValidator.validateSellerId(id);

			OrderDAO orderDAO = new OrderDAO();
			orderList = orderDAO.findOrdersBySellerId(id);

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		return orderList;

	}
	
}
