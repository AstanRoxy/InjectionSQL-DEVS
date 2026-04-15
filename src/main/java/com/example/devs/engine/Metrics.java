package com.example.devs.engine;

/** Conteneur simple pour métriques collectées par les modèles */
public class Metrics {
  public int blockedCount = 0;
  public int compromisedCount = 0;
  public int forwardedCount = 0;
  public int detectedCount = 0;

  @Override
  public String toString() {
    return "Metrics{blocked=" + blockedCount + ", compromised=" + compromisedCount + ", forwarded=" + forwardedCount + ", detected=" + detectedCount + "}";
  }
}
