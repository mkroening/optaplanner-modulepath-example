package io.github.mwkroening.optaplannermodulepathexample;

import java.util.List;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;

public class App {

  public static void main(String[] args) {
    SolverFactory<CloudBalance> solverFactory =
        SolverFactory.createFromXmlResource(
            "io/github/mwkroening/optaplannermodulepathexample/cloudBalancingSolverConfig.xml");
    Solver<CloudBalance> solver = solverFactory.buildSolver();

    CloudBalance unsolvedCloudBalance =
        new CloudBalance(
            List.of(new CloudComputer(1, 1, 1, 1)), List.of(new CloudProcess(1, 1, 1)));

    CloudBalance solvedCloudBalance = solver.solve(unsolvedCloudBalance);
  }
}
