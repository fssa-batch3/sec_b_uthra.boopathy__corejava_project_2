package in.fssa.tharasworld.validator;

import java.sql.*;
import java.util.List;
import java.util.regex.Pattern;

import in.fssa.tharasworld.dto.ProductDetailDTO;
import in.fssa.tharasworld.entity.PriceEntity;
import in.fssa.tharasworld.dao.*;
import in.fssa.tharasworld.exception.PersistenceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.util.*;

public class ProductValidator {
	
	private static final String NAME_PATTERN = "^[A-Za-z][A-Za-z\\\\s]*$";

	public static void validate(ProductDetailDTO product) throws ValidationException, PersistenceException {

		if (product == null) {
			throw new ValidationException("Invalid product input");
		}
		
		validateName(product.getName());
		
		validateImageUrl(product.getImg());
		
		validateDescription(product.getDescription());
		
		validateSellerId(product.getSellerId());
		
		validateTypeId(product.getTypeId());
		
		validatePriceList(product.getListOfPrices());
			
	}
	
	public static void validateName(String name) throws ValidationException {
		
		StringUtil.rejectIfInvalidString(name, "Product name");
	
	}
	
	public static void validateImageUrl(String imgUrl) throws ValidationException {
		
		StringUtil.rejectIfInvalidString(imgUrl, "Image url");

	}
	
	
	public static void validateSellerId(int id) throws ValidationException, PersistenceException {
		
		if(id<=0) {
			throw new ValidationException("Seller id connot be zero or in negative");
		}
		
		UserDAO userDAO = new UserDAO();
		userDAO.checkUserIsSeller(id);
		
	}
	
	public static void validateTypeId(int id) throws ValidationException, PersistenceException {
		
		if(id <= 0) {
			
			throw new ValidationException("Type id cannot be zero or in negative");
			
		}
		
		TypeDAO type = new TypeDAO();
		type.checkTypeExistWithId(id);
		
	}
	
	public static void validateDescription(String description) throws ValidationException {
		
		StringUtil.rejectIfInvalidString(description, "Description");
		
	}
	
	
	//////      PRICE VALIDATION            //////////


	public static void validateProductId(int id) throws ValidationException, PersistenceException {
		
		if(id <= 0) {
			
			throw new ValidationException("Product id cannot be zero or in negative");
			
		}
		
		ProductDAO pdt = new ProductDAO();
		pdt.checkProductExist(id);
	
	}
	
	public static void validatePriceList (List<PriceEntity> price) throws ValidationException, PersistenceException {
		
		if (price.isEmpty() || price == null) {

			throw new ValidationException("Price List cannot be null or empty");
		}
		
		
		for(PriceEntity priceList : price) {
			PriceValidator.validate(priceList);
		}
		
	}

	public static void validatePriceId(int id) throws ValidationException {
		
		if(id<=0) {
			throw new ValidationException("Invalid price id");
		}
		
	}
	
}
