package org.NAK.dao.contracts;


import org.NAK.entities.Cyclist;

import java.util.List;

public interface CyclistDAO extends GenericDAO<Cyclist> {
    List<Cyclist> findAllCyclistWithCompetitionsAndTeam();
}
