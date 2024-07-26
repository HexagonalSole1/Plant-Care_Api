package com.example.APISkeleton.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SocketIoService {

    private URI uri;
    private Map<String, List<String>> headers = new HashMap<>();
    private IO.Options options;
    private Socket socket;
    private ObjectMapper objectMapper;

    public SocketIoService() {
        try {
            uri = new URI("https://plantcaresocket.integrador.xyz/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        options = IO.Options.builder()
                .setForceNew(true)
                .setReconnection(true)
                .build();

        socket = IO.socket(uri, options);
        objectMapper = new ObjectMapper();
    }

    public void connect() {
        socket.connect();

        socket.on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("Error de conexión: " + args[0]);
            }
        });

        socket.on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("Desconectado del servidor Socket.IO");
            }
        });
    }

    public boolean isConnected() {
        return socket.connected();
    }

    public void joinRoom(String roomNumber) {
        socket.emit("join room", roomNumber);
    }

    public void sendMessage(String roomNumber, String jsonMsg, String remitente) {
        if (socket != null && socket.connected()) {
            try {
                socket.emit("chat message", jsonMsg, roomNumber, remitente);
                System.out.println("Se envió el mensaje a : " + roomNumber);
            } catch (Exception e) {
                System.err.println("Error al enviar el mensaje: " + e.getMessage());
            }
        } else {
            System.out.println("No se pudo enviar el mensaje, el socket no está conectado.");
        }
    }


    public void disconnect() {
        socket.disconnect();
    }

    public void listenToEvent(String eventName, Emitter.Listener listener) {
        socket.on(eventName, listener);
    }
    public void listenToRoomEvent(String roomNumber, String eventName, Emitter.Listener listener) {
        socket.on(eventName, listener);
    }

}
