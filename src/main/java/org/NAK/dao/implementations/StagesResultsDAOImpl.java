package org.NAK.dao.implementations;

import org.NAK.dao.contracts.StagesResultsDAO;
import org.NAK.entities.Embeded.ResultsStage;
import org.NAK.entities.StagesResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class StagesResultsDAOImpl extends GenericDAOImpl<StagesResults> implements StagesResultsDAO {
    @Autowired
    public StagesResultsDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory , StagesResults.class);
    }

    @Override
    public Optional<StagesResults> FindByCyclistAndStageId(ResultsStage embd) {
        Session session = super.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        StagesResults result = null;
        try {
            transaction.begin();
            result = session.createQuery("FROM StagesResults gr WHERE gr.resultsStage.stageId = :stageId AND gr.resultsStage.cyclistId = :cyclistId" , StagesResults.class)
                    .setParameter("stageId" , embd.getStageId())
                    .setParameter("cyclistId" , embd.getCyclistId())
                    .uniqueResult();

            transaction.commit();

            return Optional.of(result);

        }catch (RuntimeException e){
            e.printStackTrace();
        }finally{
            session.close();
        }
        return Optional.empty();
    }
}
