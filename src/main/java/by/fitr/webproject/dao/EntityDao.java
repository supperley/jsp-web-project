package by.fitr.webproject.dao;

import by.fitr.webproject.dao.exception.DaoException;
import by.fitr.webproject.entity.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface EntityDao<T extends AbstractEntity> {

    List<T> findAll() throws DaoException;

    boolean addEntity(T entity) throws DaoException;

    Optional<T> findById(Long id) throws DaoException;

    boolean delete(Long id) throws DaoException;
}
