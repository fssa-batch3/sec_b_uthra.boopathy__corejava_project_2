package in.fssa.tharasworld.service;

import java.util.Set;

import in.fssa.tharasworld.dao.UserDAO;
import in.fssa.tharasworld.exception.PersistenceException;
import in.fssa.tharasworld.exception.ServiceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.entity.UserEntity;
import in.fssa.tharasworld.validator.CategoryValidator;
import in.fssa.tharasworld.validator.UserValidator;

public class UserService {
	
	/**
	 * 
	 * @return
	 */
	
	public static Set<UserEntity> findAll() throws ServiceException {
		
		Set<UserEntity> userList = null;
				
		try {
			UserDAO userDao = new UserDAO();
					
			userList = userDao.findAll();
			
			for(UserEntity user:userList) {
				System.out.println(user);
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
				 
		return userList; 
		 
	}
	 
	/**
	 * 
	 * @param newUser
	 * @throws ValidationException
	 * @throws ServiceException
	 */

	public void create(UserEntity newUser) throws ValidationException, ServiceException {
		
		try {
			
			UserValidator.validate(newUser); 
			UserValidator.CheckUserExists(newUser.getEmail());
			UserValidator.CheckUserExistsWithPhoneNumber(newUser.getPhoneNumber());
			
			UserDAO userdao = new UserDAO();
			userdao.create(newUser);
		
		} catch (PersistenceException e) {
			e.printStackTrace(); 
			throw new ServiceException(e.getMessage());
		}
		
	}
	
	/**
	 * 
	 * @param id
	 * @param updatedUser
	 * @throws Exception
	 */
	
	public void update(int id, UserEntity updatedUser) throws ServiceException, ValidationException {
		
		try {
			UserValidator.CheckUserExistsWithId(id);
			UserValidator.validateName(updatedUser.getName());
			UserValidator.validatePassword(updatedUser.getPassword());
			UserValidator.validateAge(updatedUser.getAge());
			
			UserDAO userdao = new UserDAO();
			userdao.update(id, updatedUser);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
	} 
	
	/**
	 * 
	 * @param id
	 * @throws Exception
	 */
	
	public void delete(int id) throws ServiceException, ValidationException {
		
		try {
			UserValidator.CheckUserExistsWithId(id);
			UserValidator.CheckUserExistsWithId(id);
			
			UserDAO userdao = new UserDAO();
			userdao.delete(id);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	
	public static UserEntity findById(int id) throws ServiceException, ValidationException {
		
		UserEntity user = null;
				
		try {
			
			UserValidator.CheckUserExistsWithId(id);
			UserDAO userDao = new UserDAO();
					
			user = userDao.findById(id);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
		return user;
		
	}
	
}
