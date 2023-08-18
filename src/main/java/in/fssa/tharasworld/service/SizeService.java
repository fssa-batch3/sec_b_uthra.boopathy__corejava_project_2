package in.fssa.tharasworld.service;

import java.util.Set;

import in.fssa.tharasworld.dao.SizeDAO;
import in.fssa.tharasworld.entity.SizeEntity;
import in.fssa.tharasworld.validator.SizeValidator;

public class SizeService {
	
	/**
	 * 
	 * @return
	 */

	public Set<SizeEntity> findAll() {

		SizeDAO userDao = new SizeDAO();
		
		Set<SizeEntity> sizeList = userDao.findAll();
		
		for(SizeEntity size:sizeList) {
			System.out.println(size);
		}
				
		return sizeList; 
		
	}
	
	/**
	 * 
	 * @param newSize
	 * @throws Exception
	 */

	public void create(SizeEntity newSize) throws Exception {
		
		SizeValidator.validate(newSize);
		
		SizeDAO sizedao = new SizeDAO();
		sizedao.create(newSize);
		
	}
	
} 
