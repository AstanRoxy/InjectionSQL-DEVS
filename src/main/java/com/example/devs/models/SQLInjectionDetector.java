package com.example.devs.models;

import com.example.devs.engine.AtomicModel;

import java.util.Random;

public class SQLInjectionDetector extends AtomicModel {

    private double detectionProbability;
    private boolean detected;
    private Random random;

    public SQLInjectionDetector(String name) {
        super(name);
        random = new Random();
    }

    @Override
    public void initialize() {
        detectionProbability = 0.5; // Set the detection probability
        detected = false;
    }

    @Override
    public Object output() {
        if (detected) {
            detected = false; // Reset the flag
            return "DETECTED";
        }
        return null;
    }

    @Override
    public void internalTransition() {
        // No internal transition needed
    }

    public void receive(Object input) {
        // Simulate detection based on probability
        if (random.nextDouble() < detectionProbability) {
            detected = true;
        }
    }
}
