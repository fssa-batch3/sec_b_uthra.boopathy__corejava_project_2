package in.fssa.tharasworld.service;

import java.util.*;

import in.fssa.tharasworld.dao.ProductDAO;
import in.fssa.tharasworld.dao.PriceDAO;
import in.fssa.tharasworld.dto.ProductDetailDTO;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.entity.PriceEntity;
import in.fssa.tharasworld.validator.ProductValidator;

public class ProductService {
	
public Set<ProductDetailDTO> findAll() {
		
		ProductDAO productdao = new ProductDAO();
		PriceDAO pricedao = new PriceDAO();
		
		Set<ProductDetailDTO> productList = productdao.findAll();
		
		for(ProductDetailDTO pdt:productList) {
			List<PriceEntity> prices = pricedao.findByProductId(pdt.getPdtId());
			pdt.setListOfPrices(prices);
			System.out.println(pdt);
		}
				
		return productList; 
		
	}

	public void create(ProductDetailDTO newProduct) throws Exception {
		
		ProductDAO productdao = new ProductDAO();
		PriceDAO pricedao = new PriceDAO();

		ProductValidator.validate(newProduct);
		ProductValidator.validatePriceList(newProduct.getListOfPrices());

		int id = productdao.create(newProduct);

		for (PriceEntity newPrice : newProduct.getListOfPrices()) {
			pricedao.create(newPrice, id);
		}
		
	}
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
