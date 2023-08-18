package in.fssa.tharasworld.service;

import java.util.Set;

import in.fssa.tharasworld.dao.UserDAO;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.entity.UserEntity;
import in.fssa.tharasworld.validator.UserValidator;

public class UserService {
	
	/**
	 * 
	 * @return
	 */
	
	public Set<UserEntity> findAll() {
		
		UserDAO userDao = new UserDAO();
		
		Set<UserEntity> userList = userDao.findAll();
		
		for(UserEntity user:userList) {
			System.out.println(user);
		}
				 
		return userList; 
		 
	}
	
	/**
	 * 
	 * @param newUser
	 * @throws Exception
	 */

	public void create(UserEntity newUser) throws Exception {
		
		UserValidator.validate(newUser);
		
		UserDAO userdao = new UserDAO();
		userdao.create(newUser);
		
	}
	
	/**
	 * 
	 * @param id
	 * @param updatedUser
	 * @throws Exception
	 */
	
	public void update(int id, UserEntity updatedUser) throws Exception {
		
		UserValidator.validate(updatedUser);
		
		UserDAO userdao = new UserDAO();
		userdao.update(id, updatedUser);
		
	}
	
	/**
	 * 
	 * @param id
	 * @throws Exception
	 */
	
	public void delete(int id) throws Exception {
		
		if(id==0) {
			throw new ValidationException("Invalid id");
		}
		
		UserDAO userdao = new UserDAO();
		userdao.delete(id);
		
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	
	public UserEntity findById(int id) {
		
		UserDAO userDao = new UserDAO();
		UserEntity user = userDao.findById(id);
		return user;
		
	}
	
}
