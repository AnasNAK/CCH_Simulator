package org.NAK.dao.implementations;

import lombok.Getter;
import lombok.Setter;
import org.NAK.dao.contracts.GenericDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class GenericDAOImpl<T> implements GenericDAO<T> {

    private static final Logger logger = LoggerFactory.getLogger(GenericDAOImpl.class);
    private final SessionFactory sessionFactory;
    private final Class<T> classGeneric;

    public GenericDAOImpl(SessionFactory sessionFactory, Class<T> classGeneric) {
        this.classGeneric = classGeneric;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<T> save(T entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
            return Optional.of(entity);
        } catch (Exception e) {
            logger.error("Error occurred while saving entity", e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<T> update(T entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
            return Optional.of(entity);
        } catch (Exception e) {
            logger.error("Error occurred while updating entity", e);
            return Optional.empty();
        }
    }

    @Override
    public List<T> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from " + classGeneric.getName(), classGeneric).list();
        } catch (Exception e) {
            logger.error("Error occurred while retrieving all entities", e);
            return List.of();
        }
    }

    @Override
    public void delete(T entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(entity);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Error occurred while deleting entity", e);
        }
    }

    @Override
    public Optional<T> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.find(classGeneric, id));
        } catch (Exception e) {
            logger.error("Error occurred while finding entity by ID", e);
            return Optional.empty();
        }
    }
}
