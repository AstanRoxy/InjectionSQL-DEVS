package com.example.devs.models;

import com.example.devs.engine.AtomicModel;

/**
 * Attacker génère une charge: d'abord une requête normale, puis une injection.
 */
public class Attacker implements AtomicModel {
  private int step = 0;

  @Override
  public void initialize() { step = 0; }

  @Override
  public void receive(Object event) { /* Attacker ne reçoit rien dans ce modèle simple */ }

  @Override
  public Object output() {
    // tick 0: requête normale, tick 1: payload d'injection, ensuite rien
    if (step == 0) return "NORMAL_QUERY";
    if (step == 1) return "SQL_INJECTION_PAYLOAD";
    return null;
  }

  @Override
  public void internalTransition() { step++; }

  @Override
  public String name() { return "Attacker"; }

  @Override
  public void resetMetrics() { /* pas de métriques internes ici */ }
}
