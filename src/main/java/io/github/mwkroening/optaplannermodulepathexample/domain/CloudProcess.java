package io.github.mwkroening.optaplannermodulepathexample.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class CloudProcess {

  private int requiredCpuPower;
  private int requiredMemory;
  private int requiredNetworkBandwidth;

  private CloudComputer computer;

  public CloudProcess() {}

  public CloudProcess(int requiredCpuPower, int requiredMemory, int requiredNetworkBandwidth) {
    this.requiredCpuPower = requiredCpuPower;
    this.requiredMemory = requiredMemory;
    this.requiredNetworkBandwidth = requiredNetworkBandwidth;
  }

  public int getRequiredCpuPower() {
    return requiredCpuPower;
  }

  public void setRequiredCpuPower(int requiredCpuPower) {
    this.requiredCpuPower = requiredCpuPower;
  }

  public int getRequiredMemory() {
    return requiredMemory;
  }

  public void setRequiredMemory(int requiredMemory) {
    this.requiredMemory = requiredMemory;
  }

  public int getRequiredNetworkBandwidth() {
    return requiredNetworkBandwidth;
  }

  public void setRequiredNetworkBandwidth(int requiredNetworkBandwidth) {
    this.requiredNetworkBandwidth = requiredNetworkBandwidth;
  }

  @PlanningVariable(valueRangeProviderRefs = {"computerRange"})
  public CloudComputer getComputer() {
    return computer;
  }

  public void setComputer(CloudComputer computer) {
    this.computer = computer;
  }
}
