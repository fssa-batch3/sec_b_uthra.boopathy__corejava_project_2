package in.fssa.tharasworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import in.fssa.tharasworld.entity.UserEntity;
import in.fssa.tharasworld.exception.PersistenceException;
import in.fssa.tharasworld.exception.ValidationException;
import in.fssa.tharasworld.util.ConnectionUtil;
import in.fssa.tharasworld.util.Logger;
import in.fssa.tharasworld.util.PasswordUtil;
import in.fssa.tharasworld.interfaces.UserInterface;

public class UserDAO implements UserInterface<UserEntity> {

	/**
	 * Retrieves a set of user entities representing all active users in the
	 * database.
	 *
	 * This method queries the 'users' table to retrieve information about all
	 * active users in the system. It returns a set of UserEntity objects, each
	 * representing a user with their attributes such as name, email, age, phone
	 * number, password, role, and user ID.
	 *
	 * @return A set of UserEntity objects representing all active users.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

	@Override
	public Set<UserEntity> findAll() throws PersistenceException {

		Set<UserEntity> userList = new HashSet<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "SELECT user_id, email, user_name, age, phone_number, password, role, is_active FROM users WHERE is_active=1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {

				UserEntity user = new UserEntity();
				user.setName(rs.getString("user_name"));
				user.setPhoneNumber(rs.getLong("phone_number"));
				user.setAge(rs.getInt("age"));
				user.setId(rs.getInt("user_id"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));

				userList.add(user);

			}

		} catch (SQLException e) {

			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return userList;

	}

	/**
	 * Creates a new user in the database.
	 *
	 * This method inserts a new user into the 'users' table with the provided user
	 * information, including email, name, age, phone number, password, and role.
	 *
	 * @param newUser An instance of the UserEntity class representing the new user
	 *                to be created.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

	@Override
	public void create(UserEntity newuser) throws PersistenceException {

		Connection connection = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO users (email, user_name, age, phone_number, password, role) VALUES (?, ?, ?, ?, ?, ?)";
			connection = ConnectionUtil.getConnection();
			ps = connection.prepareStatement(query);

			ps.setString(1, newuser.getEmail());
			ps.setString(2, newuser.getName());
			ps.setInt(3, newuser.getAge());
			ps.setLong(4, newuser.getPhoneNumber());
			
			String hashPassword = PasswordUtil.encryptPassword(newuser.getPassword());
			
			ps.setString(5, hashPassword);
			ps.setString(6, newuser.getRole());

			ps.executeUpdate();

			System.out.println("User has been created successfully");

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(connection, ps);
		}

	}

	/**
	 * Updates an existing user's information in the database.
	 *
	 * This method modifies the user's information, such as name, password, and
	 * email, in the 'users' table based on the provided user ID and updated user
	 * entity.
	 *
	 * @param id          The unique identifier (user ID) of the user to be updated.
	 * @param updatedUser An instance of the UserEntity class representing the
	 *                    updated user information.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

	@Override
	public void update(int id, UserEntity updatedUser) throws PersistenceException {

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			StringBuilder queryBuilder = new StringBuilder("UPDATE users SET ");
			List<Object> values = new ArrayList<>();

			if (updatedUser.getName() != null) {
				queryBuilder.append("user_name = ?, ");
				values.add(updatedUser.getName());
			}

			if (updatedUser.getPassword() != null) {
				queryBuilder.append("password = ?, ");
				values.add(updatedUser.getPassword());
			}

			if (updatedUser.getEmail() != null) {
				queryBuilder.append("email = ?, ");
				values.add(updatedUser.getEmail());
			}

			queryBuilder.setLength(queryBuilder.length() - 2);
			queryBuilder.append(" WHERE is_active = 1 AND user_id = ?");
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(queryBuilder.toString());

			for (int i = 0; i < values.size(); i++) {
				ps.setObject(i + 1, values.get(i));
			}
			ps.setInt(values.size() + 1, id);
			ps.executeUpdate();
			System.out.println("User has been updated successfully");

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(conn, ps);

		}

	}

	/**
	 * Deletes a user from the database by marking them as inactive.
	 *
	 * This method sets the 'is_active' column to 0 for the user with the specified
	 * user ID, effectively marking the user as inactive in the 'users' table.
	 *
	 * @param id The unique identifier (user ID) of the user to be deleted.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

	@Override
	public void delete(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE users SET is_active = 0 WHERE is_active = 1 AND user_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, id);
			ps.executeUpdate();

			System.out.println("User has been deleted successfully");

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	/**
	 * Retrieves a user by their unique identifier (user ID) from the database.
	 *
	 * This method fetches a user's information from the 'users' table based on
	 * their user ID, provided as a parameter.
	 *
	 * @param id The unique identifier (user ID) of the user to retrieve.
	 * @return A UserEntity object representing the retrieved user's information.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

	@Override
	public UserEntity findById(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserEntity user = null;

		try {

			String query = "SELECT user_id, email, user_name, age, phone_number, password, role, is_active FROM users WHERE is_active = 1 AND user_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {

				user = new UserEntity();
				user.setAge(rs.getInt("age"));
				user.setId(rs.getInt("user_id"));
				user.setEmail(rs.getString("email"));
				user.setName(rs.getString("user_name"));
				user.setPhoneNumber(rs.getLong("phone_number"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));

			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}

		return user;

	}

	/**
	 * Checks if a user with a specific email address already exists in the
	 * database.
	 *
	 * This method queries the 'users' table to determine if a user with the given
	 * email address already exists.
	 *
	 * @param email The email address to check for existence in the database.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 * @throws ValidationException  If a user with the specified email address is
	 *                              found in the database, indicating that the email
	 *                              is already in use.
	 */

	public void checkUserExists(String email) throws PersistenceException, ValidationException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		UserEntity user = null;

		try {

			String query = "SELECT email FROM users WHERE is_active=1 AND email=?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();

			while (rs.next()) {
				throw new ValidationException("This user is already exist");
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}

	}

	/**
	 * Checks if a user with a specific email address exists in the database for
	 * updating.
	 *
	 * This method queries the 'users' table to determine if a user with the given
	 * email address exists and is active.
	 *
	 * @param email The email address to check for existence in the database.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 * @throws ValidationException  If no user with the specified email address is
	 *                              found in the database or if the found user is
	 *                              not active.
	 */

	public void checkUserExistsForUpdate(String email) throws PersistenceException, ValidationException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		UserEntity user = null;

		try {

			String query = "SELECT email FROM users WHERE is_active=1 AND email=?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();

			while (!rs.next()) {
				throw new ValidationException("User does not exist");
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}

	}

	/**
	 * Checks if a user with a specific ID exists in the database.
	 *
	 * This method queries the 'users' table to determine if a user with the given
	 * ID exists and is active.
	 *
	 * @param id The ID of the user to check for existence in the database.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 * @throws ValidationException  If no user with the specified ID is found in the
	 *                              database or if the found user is not active.
	 */

	public void checkUserExistsWithId(int id) throws PersistenceException, ValidationException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		UserEntity user = null;

		try {

			String query = "SELECT user_id, email, user_name, age, phone_number, password, role, is_active FROM users WHERE is_active=1 AND user_id=?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (!rs.next()) {
				throw new ValidationException("User does not exist");

			} else {
				user = new UserEntity();
				user.setId(rs.getInt("user_id"));
				user.setName(rs.getString("user_name"));
				user.setEmail(rs.getString("email"));
				user.setPhoneNumber(rs.getLong("phone_number"));
				user.setAge(rs.getInt("age"));
				user.setRole(rs.getString("role"));
				user.setPassword(rs.getString("password"));
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}

	}

	/**
	 * Checks if a user with a specific phone number exists in the database for
	 * update purposes.
	 *
	 * This method queries the 'users' table to determine if an active user with the
	 * given phone number exists. It is typically used to check the existence of a
	 * user's phone number when updating user information.
	 *
	 * @param phoneNumber The phone number of the user to check for existence in the
	 *                    database.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 * @throws ValidationException  If no active user with the specified phone
	 *                              number is found in the database.
	 */

	public void checkUserExistsWithPhoneNumberForUpdate(long phoneNumber)
			throws PersistenceException, ValidationException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		UserEntity user = null;

		try {

			String query = "SELECT user_id, email, user_name, age, phone_number, password, role, is_active FROM users WHERE is_active=1 AND phone_number=?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, phoneNumber);
			rs = ps.executeQuery();

			if (rs.next()) {
				user = new UserEntity();
				user.setId(rs.getInt("user_id"));
				user.setName(rs.getString("user_name"));
				user.setEmail(rs.getString("email"));
				user.setPhoneNumber(rs.getLong("phone_number"));
				user.setAge(rs.getInt("age"));
				user.setRole(rs.getString("role"));
				user.setPassword(rs.getString("password"));
			} else {
				throw new ValidationException("User does not exist");
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}

	}

	/**
	 * Checks if a user with a specific phone number exists in the database for
	 * update purposes.
	 *
	 * This method queries the 'users' table to determine if an active user with the
	 * given phone number exists. It is typically used to check the existence of a
	 * user's phone number when updating user information.
	 *
	 * @param phoneNumber The phone number of the user to check for existence in the
	 *                    database.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 * @throws ValidationException  If no active user with the specified phone
	 *                              number is found in the database.
	 */

	public void checkUserExistsWithPhoneNumber(long phoneNumber) throws PersistenceException, ValidationException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		UserEntity user = null;

		try {

			String query = "SELECT phone_number FROM users WHERE is_active=1 AND phone_number=?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, phoneNumber);
			rs = ps.executeQuery();

			while (rs.next()) {
				throw new ValidationException("This user is already exist");
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}

	}

	/**
	 * Checks if a user with a specific ID has the role of a seller in the database.
	 *
	 * This method queries the 'users' table to determine if the user with the
	 * provided ID has the role of a seller. It is typically used to validate
	 * whether a user is authorized to perform seller-specific actions.
	 *
	 * @param id The ID of the user to check for the seller role.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 * @throws ValidationException  If the user with the specified ID does not exist
	 *                              or is not a seller.
	 */

	public void checkUserIsSeller(int id) throws PersistenceException, ValidationException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		UserEntity user = null;

		try {

			String query = "SELECT role FROM users WHERE is_active=1 AND user_id=?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				user = new UserEntity();
				user.setRole(rs.getString("role"));

			}

			if ("buyer".equalsIgnoreCase(user.getRole())) {
				throw new ValidationException("Seller does not exist");
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}

	}

	/**
	 * Checks if a user with a specific phone number exists in the database and
	 * retrieves user details for login.
	 *
	 * This method queries the 'users' table to determine if a user with the
	 * provided phone number exists and is active. If a matching user is found,
	 * their user ID, phone number, password, and role are retrieved for login
	 * authentication.
	 *
	 * @param phoneNumber The phone number of the user to check for login.
	 * @return An instance of the UserEntity class containing user details if the
	 *         user exists and is active.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 * @throws ValidationException  If the user with the specified phone number does
	 *                              not exist or is not active.
	 */

	public UserEntity checkUserExistsWithPhoneNumberForLogin(long phoneNumber)
			throws PersistenceException, ValidationException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		UserEntity user = null;

		try {

			String query = "SELECT user_id, phone_number, password, role FROM users WHERE is_active=1 AND phone_number=?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, phoneNumber);
			rs = ps.executeQuery();

			if (rs.next()) {
				user = new UserEntity();
				user.setId(rs.getInt("user_id"));
				user.setPhoneNumber(rs.getLong("phone_number"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));

			} else {
				throw new ValidationException("User does not exist");
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}
		return user;

	}

}
