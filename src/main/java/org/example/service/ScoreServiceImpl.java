package org.example.service;

import org.example.Person;

public class ScoreServiceImpl implements ScoreService {
    @Override
    public double score(Person person) {
        return person.getScore();
    }
}
