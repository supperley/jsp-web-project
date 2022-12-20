package by.fitr.webproject.dao;

import by.fitr.webproject.dao.exception.DaoException;
import by.fitr.webproject.entity.user.User;
import by.fitr.webproject.entity.user.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserDao extends EntityDao<User> {

    @Override
    boolean addEntity(User user) throws DaoException;

    boolean authenticate(String login, String email) throws DaoException;

    @Override
    List<User> findAll() throws DaoException;

    Optional<User> findByLogin(String login) throws DaoException;

    UserRole findUserRole(String login)throws DaoException;

    boolean isLoginAvailable(String login) throws DaoException;

    boolean isEmailAvailable(String email) throws DaoException;

    boolean updatePassword(String login, String newPassword) throws DaoException;

    @Override
    Optional<User> findById(Long id) throws DaoException;

    List<User> findUsersByRole(UserRole userRole) throws DaoException;

    List<User> findUsersByQuery(String searchQuery) throws DaoException;

    @Override
    boolean delete(Long id) throws DaoException;

    boolean isCertificateValid(String serialNumber) throws DaoException;

    boolean updateUser(User user) throws DaoException;
}
