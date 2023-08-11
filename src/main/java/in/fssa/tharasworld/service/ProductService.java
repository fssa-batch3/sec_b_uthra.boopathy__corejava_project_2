package in.fssa.tharasworld.service;

import java.util.Set;

import in.fssa.tharasworld.dao.ProductDAO;
import in.fssa.tharasworld.entity.ProductEntity;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.validator.ProductValidator;

public class ProductService {
	
public Set<ProductEntity> findAll() {
		
		ProductDAO productdao = new ProductDAO();
		
		Set<ProductEntity> productList = productdao.findAll();
		
		for(ProductEntity pdt:productList) {
			System.out.println(pdt);
		}
				
		return productList; 
		
	}

	public void create(ProductEntity newProduct) throws Exception {
		
		ProductValidator.validate(newProduct);
		
		ProductDAO productdao = new ProductDAO();
		productdao.create(newProduct);
		
	}
	
	public void update(int id, ProductEntity updatedProduct) throws Exception {
		
		if(id==0) {
			throw new ValidationException("Invalid id");
		}
		
		ProductValidator.validate(updatedProduct);
		
		ProductDAO productdao = new ProductDAO();
		productdao.update(id, updatedProduct);
		
	}
	
	public void delete(int id) throws Exception {
		
		if(id==0) {
			throw new ValidationException("Invalid id");
		}
		
		ProductDAO productdao = new ProductDAO();
		productdao.delete(id);
		
	}


}
