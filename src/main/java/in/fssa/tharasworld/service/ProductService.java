package in.fssa.tharasworld.service;

import java.util.*;

import in.fssa.tharasworld.dao.ProductDAO;
import in.fssa.tharasworld.dto.ProductDetailDTO;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.model.Price;
import in.fssa.tharasworld.validator.ProductValidator;

public class ProductService {
	
public Set<ProductDetailDTO> findAll() {
		
		ProductDAO productdao = new ProductDAO();
		
		Set<ProductDetailDTO> productList = productdao.findAll();
		
		List<Price> prices = new ArrayList<>();
		
		for(ProductDetailDTO pdt:productList) {
			System.out.println(pdt);
		}
				
		return productList; 
		
	}

//	public void create(ProductDetailDTO newProduct) throws Exception {
//		
//		ProductValidator.validate(newProduct);
//		
//		ProductDAO productdao = new ProductDAO();
//		productdao.create(newProduct);
//		
//	}
//	
//	public void update(int id, ProductEntity updatedProduct) throws Exception {
//		
//		if(id==0) {
//			throw new ValidationException("Invalid id");
//		}
//		
//		ProductValidator.validate(updatedProduct);
//		
//		ProductDAO productdao = new ProductDAO();
//		productdao.update(id, updatedProduct);
//		
//	}
//	
//	public void delete(int id) throws Exception {
//		
//		if(id==0) {
//			throw new ValidationException("Invalid id");
//		}
//		
//		ProductDAO productdao = new ProductDAO();
//		productdao.delete(id);
//		
//	}


}
