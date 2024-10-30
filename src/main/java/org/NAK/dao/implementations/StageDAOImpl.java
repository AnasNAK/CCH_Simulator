package org.NAK.dao.implementations;

import org.NAK.dao.contracts.StageDAO;
import org.NAK.entities.Stage;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StageDAOImpl extends GenericDAOImpl<Stage> implements StageDAO {

    @Autowired
    public StageDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory , Stage.class);
    }
}
