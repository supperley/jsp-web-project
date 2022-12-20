package by.fitr.webproject.dao.impl;

import by.fitr.webproject.dao.exception.DaoException;
import by.fitr.webproject.dao.mapper.ColumnName;
import by.fitr.webproject.dao.mapper.impl.UserMapper;
import by.fitr.webproject.entity.user.User;
import by.fitr.webproject.entity.user.UserRole;
import by.fitr.webproject.pool.ConnectionPool;
import by.fitr.webproject.util.PasswordEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.fitr.webproject.dao.UserDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger();
    private final UserMapper userMapper = new UserMapper();

    private static final String ADD_USER = "INSERT INTO users (login, password, first_name, last_name, email, role) values (?, ?, ?, ?, ?, ?)";
    private static final String AUTHENTICATE = "SELECT users.password FROM users WHERE users.login = ?";
    private static final String SELECT_ALL_USERS = "SELECT users.id, users.login, users.password, users.first_name, users.last_name, users.email, users.role, users.certificate FROM users";
    private static final String SELECT_BY_LOGIN = "SELECT users.id, users.login, users.password, users.first_name, users.last_name, users.email, users.role, users.certificate FROM users WHERE users.login = ?";
    private static final String FIND_USER_ROLE_BY_LOGIN = "SELECT users.role FROM users WHERE users.login = ?";
    private static final String CHECK_LOGIN = "SELECT users.first_name FROM users WHERE users.login = ?";
    private static final String CHECK_EMAIL = "SELECT users.first_name FROM users WHERE users.email = ?";
    private static final String UPDATE_PASSWORD = "UPDATE users SET users.password = ?  WHERE users.login = ?";
    private static final String FIND_BY_ID = "SELECT users.id, users.login, users.password, users.first_name, users.last_name, users.email, users.role, users.certificate FROM users WHERE users.id = ?";
    private static final String DELETE_USER = "DELETE FROM users WHERE users.id = ?";
    private static final String FIND_BY_ROLE = "SELECT users.id, users.login, users.password, users.first_name, users.last_name, users.email, users.role, users.certificate FROM users WHERE users.role = ?";
    private static final String USER_SEARCH_QUERY = "SELECT users.id, users.login, users.password, users.first_name, users.last_name, users.email, users.role, users.certificate FROM users WHERE users.login LIKE CONCAT ('%', ?, '%') OR users.first_name LIKE CONCAT ('%', ?, '%') OR users.last_name LIKE CONCAT ('%', ?, '%')";
    private static final String CERTIFICATE_CHECK_FIRST = "SELECT certificates.id FROM certificates WHERE certificates.serial_number = ?";
    private static final String CERTIFICATE_CHECK_SECOND = "SELECT users.id FROM users WHERE users.certificate = ?";
    private static final String UPDATE_USER = "UPDATE users SET users.login = ?, users.first_name = ?, users.last_name = ?, users.email = ? WHERE users.id = ?";

    private static UserDaoImpl instance;

    public static UserDaoImpl getInstance() {
        if (instance == null){
            return instance = new UserDaoImpl();
        }
        return instance;
    }

    private UserDaoImpl() {
    }

    @Override
    public boolean addEntity(User user) throws DaoException {
        boolean toReturn = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER)){
            if (user != null) {
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getFirstName());
                preparedStatement.setString(4, user.getLastName());
                preparedStatement.setString(5, user.getEmail());
                preparedStatement.setString(6, user.getRole().name().toLowerCase() );
                toReturn = preparedStatement.executeUpdate() != 0;
            }
        } catch (SQLException sqlException) {
            logger.error("error in connecting the database", sqlException);
            throw new DaoException(sqlException);
        }
        return toReturn;
    }

    @Override
    public boolean authenticate(String login, String password) throws DaoException {
        if (login.isBlank() || password.isBlank()){
            return false;
        }
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(AUTHENTICATE)){
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                String passwordFromDb;
                if (resultSet.next()) {
                    passwordFromDb = resultSet.getString(1);
                    return PasswordEncoder.checkPassword(password, passwordFromDb);
                }
            }
        } catch (SQLException sqlException) {
            logger.error("error in connecting the database", sqlException);
            throw new DaoException(sqlException);
        }
        return false;
    }

    @Override
    public List<User> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS)){
            try (ResultSet resultSet = statement.executeQuery()) {
                List<User> users = new ArrayList<>();
                while (resultSet.next()) {
                    Optional<User> optionalUser = userMapper.map(resultSet);
                    optionalUser.ifPresent(users::add);
                }
                return users;
            }
        } catch (SQLException sqlException) {
            logger.error("error in connecting the database", sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public Optional<User> findByLogin(String login) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_LOGIN)){
            preparedStatement.setString(1, login);
            try ( ResultSet resultSet = preparedStatement.executeQuery()) {
                Optional<User> user;
                if (resultSet.next()) {
                    user = userMapper.map(resultSet);
                    return user;
                }
            }
        } catch (SQLException sqlException) {
            logger.error("error in connecting the database", sqlException);
            throw new DaoException(sqlException);
        }
        return Optional.empty();
    }

    @Override
    public UserRole findUserRole(String login) throws DaoException {
            UserRole userRole = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_ROLE_BY_LOGIN)){
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    userRole = UserRole.valueOf(resultSet.getString(ColumnName.ROLE).toUpperCase());
                }
            }
        } catch (SQLException sqlException) {
            logger.error("error in connecting the database", sqlException);
            throw new DaoException(sqlException);
        }
        return userRole;
    }

    @Override
    public boolean isLoginAvailable(String login) throws DaoException {
        return EmailAndLoginCheck(login, CHECK_LOGIN);
    }

    @Override
    public boolean isEmailAvailable(String email) throws DaoException {
        return EmailAndLoginCheck(email, CHECK_EMAIL);
    }

    private boolean EmailAndLoginCheck(String loginOrEmail, String check) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(check)){
            logger.info(loginOrEmail + " is login or email");
            preparedStatement.setString(1, loginOrEmail);
            logger.info(preparedStatement.toString());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                logger.info(resultSet.getString(1) + " is coming from resultset");
                boolean toReturn = !resultSet.next();
                logger.info("dao level : " + loginOrEmail + " is availability is " + toReturn);
                return toReturn;
//                return !resultSet.next();
            }
        } catch (SQLException sqlException) {
            logger.error("error in connecting the database", sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public boolean updatePassword(String login, String newHashedPassword) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD)){
            preparedStatement.setString(1, newHashedPassword);
            preparedStatement.setString(2, login);
            int result = preparedStatement.executeUpdate();
            logger.info(result == 1);
            return result == 1;
        } catch (SQLException sqlException) {
            logger.error("error in updating the user password", sqlException);
            throw new DaoException(sqlException);
        }

    }

    @Override
    public Optional<User> findById(Long id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)){
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Optional<User> user;
                if (resultSet.next()) {
                    user = userMapper.map(resultSet);
                    return user;
                }
            }
        } catch (SQLException sqlException) {
            logger.error("error in finding the user by id", sqlException);
            throw new DaoException(sqlException);
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)){
            preparedStatement.setLong(1, id);
            int count = preparedStatement.executeUpdate();
            return count == 1;
        }catch (SQLException sqlException) {
            logger.error("error in deleting the user by id", sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public List<User> findUsersByRole(UserRole userRole) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ROLE)){
            preparedStatement.setString(1, userRole.name().toLowerCase());
            return getUsersByRoleOrQuery(preparedStatement);
        } catch (SQLException sqlException) {
            logger.error("error in finding the users by role", sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public List<User> findUsersByQuery(String searchQuery) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(USER_SEARCH_QUERY)){
            preparedStatement.setString(1, searchQuery);
            preparedStatement.setString(2, searchQuery);
            preparedStatement.setString(3, searchQuery);
            return getUsersByRoleOrQuery(preparedStatement);
        } catch (SQLException sqlException) {
            logger.error("error in finding the users by search query", sqlException);
            throw new DaoException(sqlException);
        }
    }

    private List<User> getUsersByRoleOrQuery(PreparedStatement preparedStatement) throws SQLException {
        List<User> users = new ArrayList<>();
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Optional<User> user = userMapper.map(resultSet);
                user.ifPresent(users::add);
            }
        }
        return users;
    }

    @Override
    public boolean isCertificateValid(String serialNumber) throws DaoException{
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(CERTIFICATE_CHECK_FIRST);
        PreparedStatement preparedStatement1 = connection.prepareStatement(CERTIFICATE_CHECK_SECOND)){
            preparedStatement.setString(1, serialNumber);
            preparedStatement1.setString(1, serialNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery();
            ResultSet resultSet1 = preparedStatement1.executeQuery()){
                return resultSet.next() && !resultSet1.next();
            }
        } catch (SQLException sqlException) {
            logger.error("error in checking the certificate ", sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public boolean updateUser(User user) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)){
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setLong(5, user.getId());
            int count = preparedStatement.executeUpdate();
            logger.info("number of rows changed is " + count);
            return count == 1;
        } catch (SQLException sqlException) {
            logger.error("error in updating the user with id number " + user.getId(),sqlException);
            throw new DaoException(sqlException);
        }
    }
}
