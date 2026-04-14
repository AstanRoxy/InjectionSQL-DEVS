package com.example.devs.models;

import com.example.devs.engine.AtomicModel;

/**
 * Database: si elle reçoit SQL_INJECTION_PAYLOAD, elle renvoie "COMPROMISED".
 */
public class Database implements AtomicModel {
  private Object inbox = null;

  @Override
  public void initialize() { inbox = null; }

  @Override
  public void receive(Object event) { inbox = event; }

  @Override
  public Object output() {
    if (inbox == null) return null;
    if ("SQL_INJECTION_PAYLOAD".equals(inbox)) {
      return "COMPROMISED";
    } else {
      return "OK";
    }
  }

  @Override
  public void internalTransition() { inbox = null; }

  @Override
  public String name() { return "Database"; }

  @Override
  public void resetMetrics() { /* pas de métriques internes */ }
}
