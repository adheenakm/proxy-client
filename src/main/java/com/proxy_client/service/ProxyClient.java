package com.proxy_client.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Component
public class ProxyClient {
    private static final String SERVER_HOST = "offshore-proxy.com";
    private static final int SERVER_PORT = 9090;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    @PostConstruct
    public void init() {
        try {
            socket = new Socket(SERVER_HOST, SERVER_PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String forwardRequest(String requestData) {
        synchronized (this) {
            out.println(requestData);
            out.flush();
            StringBuilder response = new StringBuilder();
            try {
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response.toString();
        }
    }
}
