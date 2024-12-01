package org.example.server;

public interface ServerFactory {
    void listen(int port, Object service);
}
