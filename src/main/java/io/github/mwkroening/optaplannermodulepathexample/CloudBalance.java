package io.github.mwkroening.optaplannermodulepathexample;

import java.util.List;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

@PlanningSolution
public class CloudBalance {

  private List<CloudComputer> computerList;

  private List<CloudProcess> processList;

  private HardSoftScore score;

  public CloudBalance() {}

  public CloudBalance(List<CloudComputer> computerList, List<CloudProcess> processList) {
    this.computerList = computerList;
    this.processList = processList;
  }

  @ValueRangeProvider(id = "computerRange")
  @ProblemFactCollectionProperty
  public List<CloudComputer> getComputerList() {
    return computerList;
  }

  public void setComputerList(List<CloudComputer> computerList) {
    this.computerList = computerList;
  }

  @PlanningEntityCollectionProperty
  public List<CloudProcess> getProcessList() {
    return processList;
  }

  public void setProcessList(List<CloudProcess> processList) {
    this.processList = processList;
  }

  @PlanningScore
  public HardSoftScore getScore() {
    return score;
  }

  public void setScore(HardSoftScore score) {
    this.score = score;
  }
}
