package in.fssa.tharasworld.dao;

import java.sql.*;
import java.util.*;

import in.fssa.tharasworld.interfaces.CategoryInterface;
import in.fssa.tharasworld.entity.CategoryEntity;
import in.fssa.tharasworld.exception.PersistenceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.util.ConnectionUtil;
import in.fssa.tharasworld.util.Logger;

public class CategoryDAO implements CategoryInterface<CategoryEntity> {

	/**
	 * Retrieves a set of all active categories from the database.
	 *
	 * This method queries the 'categories' table to retrieve all categories marked
	 * as active ('is_active' = 1) and returns them as a Set of CategoryEntity
	 * objects.
	 *
	 * @return A Set containing CategoryEntity objects representing all active
	 *         categories.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

	@Override
	public Set<CategoryEntity> findAll() throws PersistenceException {

		Set<CategoryEntity> categoryList = new HashSet<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "SELECT cate_id, cate_name, img_url, is_active FROM categories WHERE is_active=1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {

				CategoryEntity category = new CategoryEntity();
				category.setCateId(rs.getInt("cate_id"));
				category.setCateName(rs.getString("cate_name"));
				category.setImg(rs.getString("img_url"));

				categoryList.add(category);

			}

		} catch (SQLException e) {

			Logger.error(e);

			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return categoryList;

	}

	/**
	 * Creates a new category in the database.
	 *
	 * This method inserts a new category into the 'categories' table with the
	 * provided category name and image URL.
	 *
	 * @param newCategory An instance of the CategoryEntity class representing the
	 *                    new category to be created.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

	@Override
	public void create(CategoryEntity newCategory) throws PersistenceException {

		Connection connection = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO categories (cate_name, img_url) VALUES (?, ?)";
			connection = ConnectionUtil.getConnection();
			ps = connection.prepareStatement(query);

			ps.setString(1, newCategory.getCateName());
			ps.setString(2, newCategory.getImg());

			ps.executeUpdate();

			System.out.println("Category has been created successfully");

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(connection, ps);
		}

	}

	/**
	 * Updates an existing category in the database.
	 *
	 * This method updates the category with the specified category ID in the
	 * 'categories' table with the provided category name and image URL.
	 *
	 * @param id              The unique identifier of the category to be updated.
	 * @param updatedCategory An instance of the CategoryEntity class representing
	 *                        the updated category information.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

	@Override
	public void update(int id, CategoryEntity updatedCategory) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query = "UPDATE categories SET cate_name=?, img_url=? WHERE is_active=1 AND cate_id=?";

			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, updatedCategory.getCateName());

			ps.setString(2, updatedCategory.getImg());

			ps.setInt(3, id);

			ps.executeUpdate();

			System.out.println("Category has been updated successfully");

		} catch (SQLException e) {

			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps);

		}

	}

	/**
	 * Marks a category as inactive in the database.
	 *
	 * This method updates the 'is_active' column to '0' (false) for a category with
	 * the specified category ID, effectively marking it as inactive and not
	 * available for further use.
	 *
	 * @param id The unique identifier of the category to be marked as inactive.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

	@Override
	public void delete(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE categories SET is_active = 0 WHERE is_active = 1 AND cate_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, id);

			ps.executeUpdate();

			System.out.println("Category has been deleted successfully");

		} catch (SQLException e) {

			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	/**
	 * Checks if a category with the specified name already exists in the database.
	 *
	 * This method queries the 'categories' table to determine if a category with
	 * the given name already exists and is marked as active ('is_active' = 1) in
	 * the database. If a category with the provided name is found, it throws a
	 * ValidationException with the message "This category name is already exists."
	 *
	 * @param name The name of the category to be checked for existence.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 * @throws ValidationException  If a category with the specified name already
	 *                              exists.
	 */

	public static void checkCategoryExist(String name) throws PersistenceException, ValidationException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		// CategoryEntity cate = null;

		try {

			String query = "SELECT cate_name FROM categories WHERE is_active=1 AND cate_name = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, name);

			rs = ps.executeQuery();

			if (rs.next()) {

				throw new ValidationException("This category name is already exists");

			}

		} catch (SQLException e) {

			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}

	}

	/**
	 * Checks if a category with the specified category ID exists in the database.
	 *
	 * This method queries the 'categories' table to determine if a category with
	 * the given category ID already exists and is marked as active ('is_active' =
	 * 1) in the database. If a category with the provided ID is not found, it
	 * throws a ValidationException with the message "Category does not exist."
	 *
	 * @param id The unique identifier of the category to be checked for existence.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 * @throws ValidationException  If a category with the specified ID does not
	 *                              exist.
	 */

	public static void checkCategoryExistWithId(int id) throws PersistenceException, ValidationException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		// CategoryEntity cate = null;

		try {

			String query = "SELECT cate_id FROM categories WHERE is_active=1 AND cate_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (!rs.next()) {
				throw new ValidationException("Category does not exists");
			}

		} catch (SQLException e) {

			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}
	}

}
