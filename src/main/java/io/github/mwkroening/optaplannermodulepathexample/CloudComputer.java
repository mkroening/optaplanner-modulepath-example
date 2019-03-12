package io.github.mwkroening.optaplannermodulepathexample;

public class CloudComputer {

  private int cpuPower;
  private int memory;
  private int networkBandwidth;
  private int cost;

  public CloudComputer() {}

  public CloudComputer(int cpuPower, int memory, int networkBandwidth, int cost) {
    this.cpuPower = cpuPower;
    this.memory = memory;
    this.networkBandwidth = networkBandwidth;
    this.cost = cost;
  }

  public int getCpuPower() {
    return cpuPower;
  }

  public void setCpuPower(int cpuPower) {
    this.cpuPower = cpuPower;
  }

  public int getMemory() {
    return memory;
  }

  public void setMemory(int memory) {
    this.memory = memory;
  }

  public int getNetworkBandwidth() {
    return networkBandwidth;
  }

  public void setNetworkBandwidth(int networkBandwidth) {
    this.networkBandwidth = networkBandwidth;
  }

  public int getCost() {
    return cost;
  }

  public void setCost(int cost) {
    this.cost = cost;
  }
}
