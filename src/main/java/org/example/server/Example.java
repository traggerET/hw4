package org.example.server;

import org.example.service.ScoreServiceImpl;

public class Example {

    public static void main(String[] args) {
        configureServer();
    }

    private static void configureServer() {
        ServerFactory serverFactory = new ServerFactoryImpl();
        serverFactory.listen(8000, new ScoreServiceImpl());
    }

}