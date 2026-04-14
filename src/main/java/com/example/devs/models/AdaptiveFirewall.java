package com.example.devs.models;

import com.example.devs.engine.AtomicModel;
import com.example.devs.engine.Metrics;

/**
 * Firewall adaptatif simple : apprend à bloquer après la première détection.
 */
public class AdaptiveFirewall implements AtomicModel {
  private Object inbox = null;
  private final Metrics metrics;
  private boolean learned = false;

  public AdaptiveFirewall(Metrics metrics) {
    this.metrics = metrics;
  }

  @Override
  public void initialize() {
    inbox = null;
    learned = false;
  }

  @Override
  public void receive(Object event) {
    inbox = event;
  }

  @Override
  public Object output() {
    if (inbox == null) return null;

    // Si déjà appris, bloque et incrémente la métrique
    if (learned && "SQL_INJECTION_PAYLOAD".equals(inbox)) {
      metrics.blockedCount++;
      return "BLOCKED";
    }

    // Si payload malveillant et pas encore appris -> bloque, apprend et incrémente la métrique
    if ("SQL_INJECTION_PAYLOAD".equals(inbox)) {
      learned = true;
      metrics.blockedCount++;
      System.out.println("AdaptiveFirewall learned");
      return "BLOCKED";
    }

    // Sinon transmet et incrémente le compteur forwarded
    metrics.forwardedCount++;
    return inbox;
  }

  @Override
  public void internalTransition() {
    inbox = null;
  }

  @Override
  public String name() {
    return "AdaptiveFirewall";
  }

  @Override
  public void resetMetrics() {
    // metrics géré globalement par l'objet Metrics partagé
  }
}
