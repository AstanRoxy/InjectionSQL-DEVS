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

    // initialisation
    fw.initialize();

    // 1) Payload malveillant : doit bloquer et incrémenter blockedCount
    fw.receive("SQL_INJECTION_PAYLOAD");
    Object out1 = fw.output();
    assertEquals("BLOCKED", out1, "Firewall should block SQL injection payloads");
    assertEquals(1, m.blockedCount, "blockedCount should be 1 after firewall blocks");

    fw.internalTransition();

    // 2) Requête normale : doit être transmise et incrémenter forwardedCount
    fw.receive("NORMAL_QUERY");
    Object out2 = fw.output();
    assertEquals("NORMAL_QUERY", out2, "Firewall should forward normal queries");
    assertEquals(1, m.forwardedCount, "forwardedCount should be 1 after forwarding a normal query");
  }
}
