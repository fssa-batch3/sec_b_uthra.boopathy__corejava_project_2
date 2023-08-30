package in.fssa.tharasworld.service;

import java.util.Set;

import in.fssa.tharasworld.dao.SizeDAO;
import in.fssa.tharasworld.entity.SizeEntity;
import in.fssa.tharasworld.exception.PersistenceException;
import in.fssa.tharasworld.exception.ServiceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.validator.SizeValidator;

public class SizeService {
	
	/**
	 * 
	 * @return
	 */

	public Set<SizeEntity> findAll() throws ServiceException {

		
		Set<SizeEntity> sizeList = null;
				
		try {
			SizeDAO userDao = new SizeDAO();

			sizeList = userDao.findAll();	
			
			for(SizeEntity size:sizeList) {
				System.out.println(size);
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
				 
		return sizeList; 
		
	}
	
	/**
	 * 
	 * @param newSize
	 * @throws ValidationException 
	 * @throws Exception
	 */

	public void create(SizeEntity newSize) throws ServiceException, ValidationException {
		
		try {
			SizeValidator.validate(newSize);
			
			SizeDAO sizedao = new SizeDAO();
			sizedao.create(newSize);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
	}
	
} 
