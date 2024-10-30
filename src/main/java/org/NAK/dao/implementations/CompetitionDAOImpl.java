package org.NAK.dao.implementations;

import org.NAK.dao.contracts.CompetitionDAO;
import org.NAK.entities.Competition;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CompetitionDAOImpl extends GenericDAOImpl<Competition> implements CompetitionDAO {
    @Autowired
    public CompetitionDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Competition.class);
    }


}
