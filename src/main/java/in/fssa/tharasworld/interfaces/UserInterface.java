package in.fssa.tharasworld.interfaces;

import java.util.*;

public interface UserInterface<T> {
	
	public abstract Set<T> findAll();
	public abstract void create(T newObject);
	public abstract void update(int id, T updatedObject);
	public abstract void delete(int id);
	public abstract T findById(int id); 
	
}
