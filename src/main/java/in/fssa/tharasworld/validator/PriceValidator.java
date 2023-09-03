package in.fssa.tharasworld.validator;

import in.fssa.tharasworld.entity.PriceEntity;
import in.fssa.tharasworld.dao.*;
import in.fssa.tharasworld.exception.PersistenceException;
import in.fssa.tharasworld.exception.ValidationException;

public class PriceValidator {
	
	public static void validate (PriceEntity price) throws ValidationException, PersistenceException {
		
		if(price == null || "".equals(price)) {
			
			throw new ValidationException("Price cannot be null or empty");
			
		}
		
//		validateSizeId(price.getSizeId());
		
		validateActualPrice(price.getActualPrice());
		
		validateCurrentPrice(price.getCurrentPrice());
		
		validateDiscount(price.getDiscount());
		
	}
	
	public static void validateSizeId(int id) throws ValidationException, PersistenceException {
		
		if(id<=0) {
			throw new ValidationException("Size id cannot be zero or in negative");
		}
		
		SizeValidator.checkSizeExistsWithId(id);
		
	}
	
	public static void validateActualPrice (double price) throws ValidationException {
		
		if (price <= 0) {
			
			throw new ValidationException("Actual price connot be zero or in negative");
			
		}
		
		
		if(price >= 100000) {
			
			throw new ValidationException("Actual price must be less than 1 lakh");
			
		}
		
	}
	
	public static void validateCurrentPrice (double price) throws ValidationException {
		
		if (price <= 0) {
			
			throw new ValidationException("Current price connot be zero or in negative");
			
		}
				
		if(price >= 100000) {
			
			throw new ValidationException("Current price must be less than 1 lakh");
			
		}
		
	}
	
	public static void validateDiscount (double discount) throws ValidationException {
		
		if (discount <= 0) {
			
			throw new ValidationException("Invalid Discount");
			
		}
		
		if(discount >= 100) {
			
			throw new ValidationException("Discount must be less than 100");
			
		}
		
	}
	
	
	
	public static void validatePriceExists (int id) throws ValidationException, PersistenceException {
		
		if(id<=0) {
			throw new ValidationException("Invalid id");
		}
		
		PriceDAO pricedao = new PriceDAO();
		pricedao.checkPriceExists(id);
		
	}
	
	public static void validateId(int id) throws ValidationException, PersistenceException {
		
		if(id<=0) {
			throw new ValidationException("Price id cannot be zero or in negative");
		}
		
		PriceDAO priceDAO = new PriceDAO();
		PriceDAO.checkPriceExists(id);

	}
	
	public static void validateProductIdAndSizeIdForUpdatePrice(int pdtId, int sizeId) throws ValidationException, PersistenceException {
		
		if(pdtId<=0 ) {
			throw new ValidationException("Product id cannot be zero or in negative");
		}
		
		if(sizeId<=0 ) {
			throw new ValidationException("Size id cannot be zero or in negative");
		}
		
		ProductDAO productDAO = new ProductDAO();
		productDAO.checkProductExist(pdtId);
		
		SizeDAO sizeDAO = new SizeDAO();
		sizeDAO.checkSizeExistWithId(sizeId);

	}

}
