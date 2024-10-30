package org.NAK.dao.contracts;
import org.NAK.entities.Embeded.GeneralResultsEmbd;
import org.NAK.entities.GeneralResults;

import java.util.Optional;

public interface GeneralResultsDAO extends GenericDAO<GeneralResults>{

    Optional<GeneralResults> FindByCompetitionAndCyclistIds(GeneralResultsEmbd generalResultsEmbd);
}
