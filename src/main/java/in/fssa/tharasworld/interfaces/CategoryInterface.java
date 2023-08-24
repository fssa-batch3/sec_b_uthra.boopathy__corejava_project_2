package in.fssa.tharasworld.interfaces;

import java.util.*;

import in.fssa.tharasworld.exception.PersistenceException;

public interface CategoryInterface<T> {
	
	public abstract Set<T> findAll() throws PersistenceException;
	public abstract void create(T newObject) throws PersistenceException;
	public abstract void update(int id, T updatedObject) throws PersistenceException;
	public abstract void delete(int id) throws PersistenceException;

}
