package in.fssa.tharasworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import in.fssa.tharasworld.interfaces.CategoryInterface;
import in.fssa.tharasworld.entity.TypeEntity;
import in.fssa.tharasworld.exception.PersistenceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.util.ConnectionUtil;
import in.fssa.tharasworld.util.Logger;

public class TypeDAO implements CategoryInterface<TypeEntity> {

	/**
	 * Retrieves a set of all active product types from the database.
	 *
	 * @return A set containing TypeEntity objects representing the product types.
	 * @throws PersistenceException If there is an issue with the database
	 *                              connection or query.
	 */

	@Override
	public Set<TypeEntity> findAll() throws PersistenceException {

		Set<TypeEntity> typeList = new HashSet<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "SELECT type_id, name, img_url, cate_id, is_active FROM types WHERE is_active=1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {

				TypeEntity type = new TypeEntity();
				type.setTypeId(rs.getInt("type_id"));
				type.setTypeName(rs.getString("name"));
				type.setImg(rs.getString("img_url"));
				type.setCateId(rs.getInt("cate_id"));

				typeList.add(type);

			}

		} catch (SQLException e) {

			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return typeList;

	}

	/**
	 * Creates a new product type in the database.
	 *
	 * This method inserts a new product type into the 'types' table with the
	 * provided name, image URL, and category ID.
	 *
	 * @param newType An instance of the TypeEntity class representing the new
	 *                product type to be created.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

	@Override
	public void create(TypeEntity newType) throws PersistenceException {

		Connection connection = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO types (name, img_url, cate_id) VALUES (?, ?, ?)";
			connection = ConnectionUtil.getConnection();
			ps = connection.prepareStatement(query);

			ps.setString(1, newType.getTypeName());
			ps.setString(2, newType.getImg());
			ps.setInt(3, newType.getCateId());

			ps.executeUpdate();

			Logger.info("Type has been created successfully");

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(connection, ps);
		}

	}

	/**
	 * Updates an existing product type in the database.
	 *
	 * This method updates the attributes of an existing product type in the 'types'
	 * table with the provided type ID.
	 *
	 * @param id          The unique identifier of the product type to be updated.
	 * @param updatedType An instance of the TypeEntity class containing the updated
	 *                    attributes for the product type.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

	@Override
	public void update(int id, TypeEntity updatedType) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query = "UPDATE types SET name=?, img_url=?, cate_id=? WHERE is_active=1 AND type_id=?";

			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, updatedType.getTypeName());
			ps.setString(2, updatedType.getImg());
			ps.setInt(3, updatedType.getCateId());
			ps.setInt(4, id);

			ps.executeUpdate();

			Logger.info("Type has been updated successfully");

		} catch (SQLException e) {

			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps);

		}

	}

	/**
	 * Deletes a product type from the database.
	 *
	 * This method marks a product type as inactive in the 'types' table based on
	 * the provided type ID.
	 *
	 * @param id The unique identifier of the product type to be deleted.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

	@Override
	public void delete(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE types SET is_active = 0 WHERE is_active = 1 AND type_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, id);

			ps.executeUpdate();

			Logger.info("Type has been deleted successfully");

		} catch (SQLException e) {

			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	/**
	 * Retrieves a set of product types associated with a specific category from the
	 * database.
	 *
	 * This method queries the 'types' table to retrieve all active product types
	 * that belong to the specified category.
	 *
	 * @param id The unique identifier of the category for which product types are
	 *           to be retrieved.
	 * @return A set of TypeEntity instances representing the product types
	 *         associated with the specified category.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

	public Set<TypeEntity> findAllTypeByCategoryId(int id) throws PersistenceException {

		Set<TypeEntity> typeList = new HashSet<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "SELECT type_id, name, img_url, cate_id, is_active FROM types WHERE is_active=1 AND cate_id=?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {

				TypeEntity type = new TypeEntity();
				type.setTypeId(rs.getInt("type_id"));
				type.setTypeName(rs.getString("name"));
				type.setImg(rs.getString("img_url"));
				type.setCateId(rs.getInt("cate_id"));

				typeList.add(type);

			}

		} catch (SQLException e) {

			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return typeList;

	}

	/**
	 * Checks if a product type with a given name already exists in the database.
	 *
	 * This method queries the 'types' table to determine whether a product type
	 * with the specified name is already present and active in the database.
	 *
	 * @param name The name of the product type to be checked for existence.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 * @throws ValidationException  If a product type with the specified name
	 *                              already exists in the database.
	 */

	public void checkTypeExistWithName(String name) throws PersistenceException, ValidationException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "SELECT name FROM types WHERE is_active=1 AND name = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, name);

			rs = ps.executeQuery();

			if (rs.next()) {

				throw new ValidationException("This type name is already exists");

			}

		} catch (SQLException e) {

			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}

	}

	/**
	 * Checks if a product type with a given ID exists and is active in the
	 * database.
	 *
	 * This method queries the 'types' table to determine whether a product type
	 * with the specified ID exists and is active in the database.
	 *
	 * @param id The ID of the product type to be checked for existence.
	 * @return A TypeEntity object representing the product type if it exists and is
	 *         active.
	 * @throws ValidationException  If no product type with the specified ID is
	 *                              found or if it is not active.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

	public TypeEntity checkTypeExistWithId(int id) throws ValidationException, PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		TypeEntity type = null;

		try {

			String query = "SELECT type_id, name FROM types WHERE is_active=1 AND type_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {

				type = new TypeEntity();
				type.setTypeId(rs.getInt("type_id"));
				type.setTypeName(rs.getString("name"));

			} else {

				throw new ValidationException("Type id does not exists");

			}

		} catch (SQLException e) {

			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}

		return type;

	}

	/**
	 * Checks if a category with a given ID exists and is active in the database.
	 *
	 * This method queries the 'categories' table to determine whether a category
	 * with the specified ID exists and is active in the database.
	 *
	 * @param id The ID of the category to be checked for existence.
	 * @throws ValidationException  If no category with the specified ID is found or
	 *                              if it is not active.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

	public static void checkCategoryIdExists(int id) throws PersistenceException, ValidationException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

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
