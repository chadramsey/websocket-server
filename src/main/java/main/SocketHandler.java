package main;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class SocketHandler extends TextWebSocketHandler {

    List<WebSocketSession> sessions = new ArrayList();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        for(WebSocketSession webSocketSession : sessions) {
            Map value = new Gson().fromJson(message.getPayload(), Map.class);
            webSocketSession.sendMessage(new TextMessage("Coordinates: (" + value.get("coordinates") + ")"));
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
    }
}
