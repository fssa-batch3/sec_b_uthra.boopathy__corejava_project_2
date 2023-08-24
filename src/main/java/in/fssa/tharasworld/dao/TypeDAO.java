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

public class TypeDAO implements CategoryInterface<TypeEntity> {

	/**
	 * @return
	 */

	@Override
	public Set<TypeEntity> findAll() throws PersistenceException {

		Set<TypeEntity> typeList = new HashSet<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null; 

		try {

			String query = "SELECT * FROM types WHERE is_active=1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {

				TypeEntity type = new TypeEntity();
				type.setTypeId(rs.getInt("type_id"));
				type.setTypeName(rs.getString("name"));
				type.setCateId(rs.getInt("cate_id"));

				typeList.add(type);

			}

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return typeList;

	}

	/**
	 *
	 * @param newType
	 * 	
	 */
	
	@Override
	public void create(TypeEntity newType) throws PersistenceException{

		Connection connection = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO types (name, cate_id) VALUES (?, ?)";
			connection = ConnectionUtil.getConnection();
			ps = connection.prepareStatement(query);

			ps.setString(1, newType.getTypeName());
			ps.setInt(2, newType.getCateId());

			ps.executeUpdate();

			System.out.println("Type has been created successfully");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(connection, ps);
		}

	}
	
	/**
	 * @param id, updatedType
	 */

	@Override
	public void update(int id, TypeEntity updatedType) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query = "UPDATE types SET name=?, cate_id=? WHERE is_active=1 AND type_id=?";

			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, updatedType.getTypeName());
			ps.setInt(2, updatedType.getCateId());
			ps.setInt(3, updatedType.getTypeId());

			ps.executeUpdate();

			System.out.println("Type has been updated successfully");

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps);

		}

	}

	/**
	 * @param id
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

			System.out.println("Type has been deleted successfully");

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps);
		}

	}
	
	/**
	 * 
	 * @param name
	 * @throws ValidationException
	 */

	public void checkTypeExistWithName(String name) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "SELECT * FROM types WHERE is_active=1 AND name = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, name);

			rs = ps.executeQuery();

			if (rs.next()) {

				throw new PersistenceException("This type name is already exists");

			}

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}

	}
	
	/**
	 * 
	 * @param name
	 * @throws ValidationException
	 */
	
	public void checkTypeExistWithId(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "SELECT type_id FROM types WHERE is_active=1 AND type_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (!rs.next()) {

				throw new PersistenceException("Type id does not exists");

			}

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}

	}
	
	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 */

	public static void checkCategoryIdExists(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "SELECT * FROM categories WHERE is_active=1 AND cate_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (!rs.next()) {

				throw new PersistenceException("Category does not exists");

			}

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}

	}

}
