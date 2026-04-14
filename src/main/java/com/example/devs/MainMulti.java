
package com.example.devs;

import com.example.devs.engine.Metrics;
import com.example.devs.engine.SimpleCoupledSimulator;
import com.example.devs.engine.AtomicModel;
import com.example.devs.models.Attacker;
import com.example.devs.models.Firewall;
import com.example.devs.models.Database;
import com.example.devs.models.AdaptiveFirewall;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Exécute plusieurs scénarios et écrit un CSV de résultats.
 */
public class MainMulti {
  public static void main(String[] args) {
    String csvPath = "results.csv";
    int runsPerScenario = 20;
    int ticks = 4;

    try (PrintWriter out = new PrintWriter(new FileWriter(csvPath))) {
      out.println("scenario,run,blocked,compromised,forwarded");

      // Scénarios : baseline, attackOnly, staticDefense, adaptiveDefense
      for (int run = 1; run <= runsPerScenario; run++) {
        // 1) Baseline : pas d'attaquant (simulateur vide ou seulement DB with normal traffic)
        Metrics m1 = new Metrics();
        Attacker attackerNone = new Attacker() {
          @Override public Object output() { return null; } // override to produce nothing
        };
        Database db1 = new Database();
        List<AtomicModel> chain1 = new ArrayList<>();
        chain1.add(attackerNone);
        chain1.add(db1);
        SimpleCoupledSimulator sim1 = new SimpleCoupledSimulator(chain1, m1);
        sim1.initialize();
        sim1.run(ticks);
        out.printf("baseline,%d,%d,%d,%d%n", run, m1.blockedCount, m1.compromisedCount, m1.forwardedCount);

        // 2) Attack only : Attacker -> Database
        Metrics m2 = new Metrics();
        Attacker attacker2 = new Attacker();
        Database db2 = new Database();
        List<AtomicModel> chain2 = new ArrayList<>();
        chain2.add(attacker2);
        chain2.add(db2);
        SimpleCoupledSimulator sim2 = new SimpleCoupledSimulator(chain2, m2);
        sim2.initialize();
        sim2.run(ticks);
        out.printf("attack_only,%d,%d,%d,%d%n", run, m2.blockedCount, m2.compromisedCount, m2.forwardedCount);

        // 3) Static defense : Attacker -> Firewall -> Database
        Metrics m3 = new Metrics();
        Attacker attacker3 = new Attacker();
        Firewall fw3 = new Firewall(m3);
        Database db3 = new Database();
        List<AtomicModel> chain3 = new ArrayList<>();
        chain3.add(attacker3);
        chain3.add(fw3);
        chain3.add(db3);
        SimpleCoupledSimulator sim3 = new SimpleCoupledSimulator(chain3, m3);
        sim3.initialize();
        sim3.run(ticks);
        out.printf("static_defense,%d,%d,%d,%d%n", run, m3.blockedCount, m3.compromisedCount, m3.forwardedCount);

        // 4) Adaptive defense : Attacker -> AdaptiveFirewall -> Database
        Metrics m4 = new Metrics();
        Attacker attacker4 = new Attacker();
        AdaptiveFirewall afw = new AdaptiveFirewall(m4);
        Database db4 = new Database();
        List<AtomicModel> chain4 = new ArrayList<>();
        chain4.add(attacker4);
        chain4.add(afw);
        chain4.add(db4);
        SimpleCoupledSimulator sim4 = new SimpleCoupledSimulator(chain4, m4);
        sim4.initialize();
        sim4.run(ticks);
        out.printf("adaptive_defense,%d,%d,%d,%d%n", run, m4.blockedCount, m4.compromisedCount, m4.forwardedCount);
      }

      System.out.println("All runs finished. Results written to " + csvPath);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

