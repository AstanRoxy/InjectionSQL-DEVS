package com.example.devs.models;

import com.example.devs.engine.Metrics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour AdaptiveFirewall.
 */
public class AdaptiveFirewallTest {

  @Test
  void blocksLearnsAndForwards() {
    Metrics m = new Metrics();
    AdaptiveFirewall af = new AdaptiveFirewall(m);

    af.initialize();

    af.receive("SQL_INJECTION_PAYLOAD");
    Object out1 = af.output();
    assertEquals("BLOCKED", out1);
    assertEquals(1, m.blockedCount);

    af.internalTransition();

    af.receive("SQL_INJECTION_PAYLOAD");
    Object out2 = af.output();
    assertEquals("BLOCKED", out2);
    assertEquals(2, m.blockedCount);

    af.internalTransition();

    af.receive("NORMAL_QUERY");
    Object out3 = af.output();
    assertEquals("NORMAL_QUERY", out3);
    assertEquals(1, m.forwardedCount);
  }
}
