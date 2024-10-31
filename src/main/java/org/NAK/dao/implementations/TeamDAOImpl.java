package org.NAK.dao.implementations;


import org.NAK.dao.contracts.TeamDAO;
import org.NAK.entities.Team;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TeamDAOImpl extends GenericDAOImpl<Team> implements TeamDAO {


    public TeamDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory , Team.class);
    }
}
