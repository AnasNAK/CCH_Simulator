package org.NAK.dao.implementations;

import lombok.Getter;
import lombok.Setter;
import org.NAK.dao.contracts.GenericDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;


@Getter
@Setter
public class GenericDAOImpl<T> implements GenericDAO<T> {


    private SessionFactory sessionFactory;

    private final Class<T> classGeneric;


    public GenericDAOImpl(SessionFactory sessionFactory ,  Class<T> classGeneric) {
        this.classGeneric = classGeneric;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<T> save(T entity) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try{
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
            return Optional.of(entity);
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()){
                transaction.rollback();
            }
            e.printStackTrace();
            return Optional.empty();
        }finally {
            session.close();
        }
    }

    @Override
    public Optional<T> update(T entity) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
            return Optional.of(entity);
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return Optional.empty();
        }finally {
            session.close();
        }
    }

    @Override
    public List<T> findAll() {
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            return session.createQuery("from "+classGeneric.getName() , classGeneric).list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }finally {
            session.close();
        }
    }

    @Override
    public void delete(T entity) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try{
            transaction = session.getTransaction();
            transaction.begin();
            session.remove(entity);
            transaction.commit();
        }catch (Exception e){
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public Optional<T> findById(Long id) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try{
            transaction = session.beginTransaction();
            T fake = session.find(classGeneric , id);
            transaction.commit();
            return Optional.ofNullable(fake);
        }catch(Exception e){
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
            return Optional.empty();
        }finally {
            session.close();
        }
    }



}
