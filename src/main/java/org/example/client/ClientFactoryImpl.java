package org.example.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class ClientFactoryImpl implements ClientFactory {
    private final String address;
    private final int port;

    public ClientFactoryImpl(String address, int port) {
        this.address = address;
        this.port = port;
    }

    @Override
    public <T> T newClient(Class<T> client) {
        return client.cast(
                Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                new Class<?>[] {
                        client
                },
                (proxy, method, args) -> {
                    try (
                            Socket sock = new Socket(address, port);
                            ObjectOutputStream sout = new ObjectOutputStream(sock.getOutputStream());
                            ObjectInputStream sin = new ObjectInputStream(sock.getInputStream())
                    ) {
                        sout.writeObject(method.getName());
                        sout.writeObject(method.getParameterTypes());
                        sout.writeObject(args);

                        return sin.readObject();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    return null;
                }
            )
        );
    }
}