module io.github.mwkroening.optaplannermodulepathexample {
  requires org.optaplanner.core;
  requires org.slf4j;

  opens io.github.mwkroening.optaplannermodulepathexample.domain;

  opens io.github.mwkroening.optaplannermodulepathexample.solver;
}
