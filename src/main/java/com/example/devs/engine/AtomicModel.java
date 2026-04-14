package com.example.devs.engine;




/**
 * Interface minimale pour un modèle atomique DEVS pédagogique.
 */
public interface AtomicModel {
  /** initialisation du modèle */
  void initialize();

  /** recevoir un événement externe (synchronous simple API) */
  void receive(Object event);

  /** produire une sortie (null si aucune) */
  Object output();

  /** transition interne (après output) */
  void internalTransition();

  /** nom du modèle pour logs */
  String name();

  /** reset métriques internes */
  void resetMetrics();
}
