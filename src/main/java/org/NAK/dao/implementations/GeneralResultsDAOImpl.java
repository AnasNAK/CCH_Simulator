package org.NAK.dao.implementations;

import jakarta.persistence.EntityNotFoundException;
import org.NAK.dao.contracts.GeneralResultsDAO;
import org.NAK.entities.Embeded.GeneralResultsEmbd;
import org.NAK.entities.GeneralResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class GeneralResultsDAOImpl extends GenericDAOImpl<GeneralResults> implements GeneralResultsDAO {

    public GeneralResultsDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory , GeneralResults.class);
    }

    @Override
    public Optional<GeneralResults> FindByCompetitionAndCyclistIds(GeneralResultsEmbd generalResultsEmbd) {
        Transaction tx = null;
        Session session = super.getSessionFactory().openSession();
        GeneralResults result = null;
        try {
            tx = session.beginTransaction();
            result = session.createQuery("FROM GeneralResults gr WHERE gr.generalResults.CyclistId = :cyclistId AND gr.generalResults.CompetitionId = :competitionId", GeneralResults.class)
                    .setParameter("cyclistId", generalResultsEmbd.getCyclistId())
                    .setParameter("competitionId", generalResultsEmbd.getCompetitionId())
                    .uniqueResult();
            tx.commit();
        }catch (EntityNotFoundException e){
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
        return Optional.ofNullable(result);
    }
}
