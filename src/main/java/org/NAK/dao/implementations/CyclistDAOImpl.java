package org.NAK.dao.implementations;

import org.NAK.dao.contracts.CyclistDAO;
import org.NAK.entities.Cyclist;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CyclistDAOImpl extends GenericDAOImpl<Cyclist> implements CyclistDAO {
    public CyclistDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory , Cyclist.class);
    }

    @Override
    public List findAllCyclistWithCompetitionsAndTeam() {
        Session session = this.getSessionFactory().openSession();
        try{
            return session.createQuery("from Cyclist c").list();
        }catch(Exception e){
            e.printStackTrace();
            return List.of();
        }
    }

}