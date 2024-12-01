package org.example.client;

import org.example.Person;
import org.example.service.ScoreService;

public class Example {
    public static void main(String[] args) {
        ScoreService scoreService = createScoreClient();

        double score = scoreService.score(new Person("Berry", 10));
        System.out.println(score);
    }

    private static ScoreService createScoreClient() {
        ClientFactory factory = new ClientFactoryImpl("127.0.0.1", 8000);
        return factory.newClient(ScoreService.class);
    }
}
