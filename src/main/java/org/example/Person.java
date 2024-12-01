package org.example;

import java.io.Serializable;

public class Person implements Serializable {
    private final String name;
    private double score;

    public Person(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
