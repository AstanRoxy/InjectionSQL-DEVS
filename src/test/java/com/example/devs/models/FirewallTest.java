package com.example.devs.models;

import com.example.devs.engine.Metrics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour Firewall.
 */
public class FirewallTest {

  @Test
  void blocksMaliciousAndForwardsNormal() {
    Metrics m = new Metrics();
    Firewall fw = new Firewall(m);

    fw.initialize();

    fw.receive("SQL_INJECTION_PAYLOAD");
    Object out1 = fw.output();
    assertEquals("BLOCKED", out1);
    assertEquals(1, m.blockedCount);

    fw.internalTransition();

    fw.receive("NORMAL_QUERY");
    Object out2 = fw.output();
    assertEquals("NORMAL_QUERY", out2);
    assertEquals(1, m.forwardedCount);
  }
}
