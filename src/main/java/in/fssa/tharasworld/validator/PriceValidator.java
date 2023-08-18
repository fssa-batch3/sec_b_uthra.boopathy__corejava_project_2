package in.fssa.tharasworld.validator;

import in.fssa.tharasworld.entity.PriceEntity;
import in.fssa.tharasworld.exception.ValidationException;

public class PriceValidator {
	
	public static void validate (PriceEntity price) throws ValidationException {
		
		if(price == null) {
			
			throw new ValidationException("Price cannot be null");
			
		}
		
		validateActualPrice(price.getActualPrice());
		validateCurrentPrice(price.getCurrentPrice());
		validateDiscount(price.getDiscount());
		
	}
	
	public static void validateActualPrice (double price) throws ValidationException {
		
		if (price <= 0) {
			
			throw new ValidationException("Invalid actual price");
			
		}
		
		String disInString = String.valueOf(price);
		
		if(disInString.length() > 6) {
			
			throw new ValidationException("Actual price must be less than 1 lakh");
			
		}
		
	}
	
	public static void validateCurrentPrice (double price) throws ValidationException {
		
		if (price <= 0) {
			
			throw new ValidationException("Invalid actual price");
			
		}
		
		String disInString = String.valueOf(price);
		
		if(disInString.length() > 6) {
			
			throw new ValidationException("Current price must be less than 1 lakh");
			
		}
		
	}
	
	public static void validateDiscount (double discount) throws ValidationException {
		
		if (discount <= 0) {
			
			throw new ValidationException("Invalid Discount");
			
		}
		
		String disInString = String.valueOf(discount);
		
		if(disInString.length() >= 3) {
			
			throw new ValidationException("Discount must be less than 100");
			
		}
		
	}

}
