package in.fssa.tharasworld.validator;

import in.fssa.tharasworld.dao.AddressDAO;
import in.fssa.tharasworld.dao.OrderDAO;
import in.fssa.tharasworld.entity.OrderEntity;
import in.fssa.tharasworld.exception.PersistenceException;
import in.fssa.tharasworld.exception.ValidationException;

public class OrderValidator {
	
	public static void validate(OrderEntity order) throws ValidationException, PersistenceException {

		if (order == null) {
			throw new ValidationException("Invalid order input");
		}
		
		UserValidator.validatePhoneNumber(order.getPhoneNumber());

		validateQuantity(order.getQuantity());
		
		UserValidator.CheckUserExistsWithId(order.getUserId());
		
		PriceValidator.validatePriceExists(order.getPriceId());
		
		validateAddressId(order.getAddressId());
		
		ProductValidator.validateSellerId(order.getSellerId());
		
	}
	
	public static void validateQuantity(int quantity) throws ValidationException {

		if (quantity <= 0 || quantity > 10) {
			throw new ValidationException("Quantity cannot be less than or equal to zero or greater than 10");
		}

	}
	
	public static void validateAddressId(int id) throws ValidationException, PersistenceException {

		if (id <= 0) {
			throw new ValidationException("Address id cannot be zero or in negative");
		}

		AddressDAO addressDAO = new AddressDAO();
		addressDAO.checkAddressExistWithAddressId(id);

	}
	
	
	public static void validateOrderId(int id) throws ValidationException, PersistenceException {

		if (id <= 0) {
			throw new ValidationException("Order id cannot be zero or in negative");
		}

		OrderDAO orderDAO = new OrderDAO();
		orderDAO.checkorderExistWithOrderId(id);

	}

}
