package org.NAK.dao.contracts;

import org.NAK.entities.Embeded.ResultsStage;
import org.NAK.entities.StagesResults;

import java.util.Optional;

public interface StagesResultsDAO extends GenericDAO<StagesResults>{

    Optional<StagesResults> FindByCyclistAndStageId(ResultsStage embd);
}
