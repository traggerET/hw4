package org.example.client;

public interface ClientFactory {
    <T> T newClient(Class<T> client);
}
