package com.example.devs.models;

import com.example.devs.engine.AtomicModel;
import com.example.devs.engine.Metrics;

/**
 * Firewall simple: bloque si payload contient "SQL_INJECTION_PAYLOAD".
 */
public class Firewall implements AtomicModel {
  private Object inbox = null;
  private final Metrics metrics;

  public Firewall(Metrics metrics) { this.metrics = metrics; }

  @Override
  public void initialize() { inbox = null; }

  @Override
  public void receive(Object event) { inbox = event; }

  @Override
  public Object output() {
    if (inbox == null) return null;
    if ("SQL_INJECTION_PAYLOAD".equals(inbox)) {
      // bloque l'attaque et incrémente la métrique
      metrics.blockedCount++;
      return "BLOCKED";
    } else {
      // transmet la requête normale et incrémente forwardedCount
      metrics.forwardedCount++;
      return inbox;
    }
  }

  @Override
  public void internalTransition() { inbox = null; }

  @Override
  public String name() { return "Firewall"; }

  @Override
  public void resetMetrics() { /* metrics géré globalement */ }
}
