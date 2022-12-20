package by.fitr.webproject.service.impl;

import by.fitr.webproject.service.exception.ServiceException;
import by.fitr.webproject.validator.UserValidator;
import by.fitr.webproject.validator.impl.UserValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.fitr.webproject.dao.UserDao;
import by.fitr.webproject.dao.exception.DaoException;
import by.fitr.webproject.dao.impl.UserDaoImpl;
import by.fitr.webproject.entity.user.User;
import by.fitr.webproject.entity.user.UserRole;
import by.fitr.webproject.service.UserService;
import by.fitr.webproject.util.PasswordEncoder;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger();
    private final UserValidator userValidator = UserValidatorImpl.getInstance();

    private static UserServiceImpl instance;
    private final UserDao userDao = UserDaoImpl.getInstance();

    public static UserServiceImpl getInstance() {
        if (instance == null){
            return instance = new UserServiceImpl();
        }
        return instance;
    }

    private UserServiceImpl() {
    }

    @Override
    public boolean registerUser(User user) throws ServiceException {
        if (userValidator.checkUser(user)){
            user.setPassword(PasswordEncoder.hashPassword(user.getPassword()));
            try {
                return userDao.addEntity(user);
            } catch (DaoException e) {
                logger.error("error in adding user in service layer", e);
                throw new ServiceException(e);
            }
        }else {
            return false;
        }
    }

    @Override
    public boolean authenticate(String login, String password) throws ServiceException {
        boolean match;
        try {
//            String hashedPassword = PasswordEncoder.hashPassword(password);
            match = userDao.authenticate(login, password);
            logger.info(match);
        } catch (DaoException e) {
            logger.error("error in authenticating the user", e);
            throw new ServiceException(e);
        }
        return match;
    }

    @Override
    public List<User> findAll() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            logger.error("error in getting users", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> findByLogin(String login) throws ServiceException {
        try {
            return userDao.findByLogin(login);
        } catch (DaoException e) {
            logger.error("error in finding user by login", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public UserRole findUserRole(String login) throws ServiceException {
        try {
            return userDao.findUserRole(login);
        } catch (DaoException e) {
            logger.error("error in finding user role by login", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isLoginAvailable(String login) throws ServiceException {
        try {
            return userDao.isLoginAvailable(login);
        } catch (DaoException e) {
            logger.error("error in finding out whether login is available or not", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isEmailAvailable(String email) throws ServiceException {
        try {
            return userDao.isEmailAvailable(email);
        } catch (DaoException e) {
            logger.error("error in finding out whether email is available or not", e);
            throw new ServiceException(e);
        }
    }



    @Override
    public boolean updatePassword(String login, String newPassword) throws ServiceException {
        if (userValidator.checkPassword(newPassword)) {
            logger.info(newPassword);
            logger.info("after passing user validator password");
            String newHashedPassword = PasswordEncoder.hashPassword(newPassword);
            try {
                logger.info(userDao.updatePassword(login, newHashedPassword));
                return userDao.updatePassword(login, newHashedPassword);
            } catch (DaoException e) {
                logger.error("error in updating the password", e);
                throw new ServiceException(e);
            }
        }else {
            logger.info(newPassword);
            logger.info("not passing user validator password");
            return false;
        }
    }

    @Override
    public Optional<User> findById(Long id) throws ServiceException {
        try {
            return userDao.findById(id);
        } catch (DaoException e) {
            logger.error("error in finding the user by id", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findUsersByRole(UserRole userRole) throws ServiceException {
        try {
            return userDao.findUsersByRole(userRole);
        } catch (DaoException e) {
            logger.error("error in finding all the users by user role", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findUsersByQuery(String searchQuery) throws ServiceException {
        try {
            return userDao.findUsersByQuery(searchQuery);
        } catch (DaoException e) {
            logger.error("error in finding all the users by search query", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(Long id) throws ServiceException {
        try {
            return userDao.delete(id);
        } catch (DaoException e) {
            logger.error("error in deleting the user by id", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isCertificateValid(String serialNumber) throws ServiceException {
        try {
            return userDao.isCertificateValid(serialNumber);
        } catch (DaoException e) {
            logger.error("error in deleting the user by id", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateUser(User user) throws ServiceException {
        if (userValidator.checkFirstName(user.getFirstName()) && userValidator.checkLastName(user.getLastName())
        && userValidator.checkLogin(user.getLogin()) && userValidator.checkEmail(user.getEmail())) {
            try {
                return userDao.updateUser(user);
            } catch (DaoException e) {
                logger.error("error in updating the user by id", e);
                throw new ServiceException(e);
            }
        }else {
            return false;
        }
    }
}
