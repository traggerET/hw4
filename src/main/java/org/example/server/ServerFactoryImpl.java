package org.example.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFactoryImpl implements ServerFactory {
    @Override
    public void listen(int port, Object service) {
        try (ServerSocket server = new ServerSocket(port)) {
            while (true) {
                Socket client = server.accept();
                Thread.startVirtualThread(() -> {
                    try (
                            ObjectOutputStream sout = new ObjectOutputStream(client.getOutputStream());
                            ObjectInputStream sin = new ObjectInputStream(client.getInputStream())
                    ) {
                        String method = (String) sin.readObject();
                        Class<?>[] params = (Class<?>[]) sin.readObject();
                        Object[] args = (Object[]) sin.readObject();

                        sout.writeObject(service.getClass().getMethod(method, params).invoke(service, args));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                });
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

