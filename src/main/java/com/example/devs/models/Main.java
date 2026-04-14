package com.example.devs.models;

import com.example.devs.engine.Metrics;
import com.example.devs.engine.SimpleCoupledSimulator;
import com.example.devs.engine.AtomicModel;


import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    Metrics metrics = new Metrics();

    // Scénario AVEC firewall
    Attacker attacker = new Attacker();
    Firewall firewall = new Firewall(metrics);
    Database database = new Database();

    List<AtomicModel> chain = new ArrayList<>();
    chain.add(attacker);
    chain.add(firewall);
    chain.add(database);

    SimpleCoupledSimulator sim = new SimpleCoupledSimulator(chain, metrics);
    sim.initialize();
    sim.run(4); // exécute 4 ticks
  }
}
