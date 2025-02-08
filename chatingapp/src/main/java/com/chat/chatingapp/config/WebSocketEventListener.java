package com.chat.chatingapp.config;

import com.chat.chatingapp.chat.ChatMessage;
import com.chat.chatingapp.chat.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {
    final SimpMessageSendingOperations messageTemplate;

    @EventListener
    public void handleWebSocketDisconnectionListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");

        if (username != null) {
            // You had a typo in the MessageType (LEVER → LEAVE)
            ChatMessage chatMessage = ChatMessage.builder()
                    .type(MessageType.LEAVE)  // Ensure LEAVE is correctly used here
                    .sender(username)
                    .build();
            // Send to the correct topic
            messageTemplate.convertAndSend("/topic/public", chatMessage); // Corrected the topic path
        }
    }
}
