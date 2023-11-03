package com.example.springwebmvctemplate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class WebSocketHelloController {
  private final SimpMessagingTemplate messagingTemplate;

  @MessageMapping("/hello")
  public void hello(@Header("simpSessionId") String sessionId) {
    messagingTemplate.convertAndSend("/topic/hello", sessionId + ", nice to meet you!");
  }

  @MessageMapping("/hello-to-one")
  public void helloToOne(@Header("simpSessionId") String sessionId) {
    messagingTemplate.convertAndSendToUser(
        sessionId,
        "/topic/hello",
        sessionId + ", nice to meet you only!",
        createHeaders(
            sessionId)); // destination automatically set to /user/topic/hello (user destination
    // prefix is added)
  }

  // to send message to specific user using SimpMessagingTemplate, header has to be explicitly set
  private MessageHeaders createHeaders(String sessionId) {
    SimpMessageHeaderAccessor headerAccessor = StompHeaderAccessor.create(SimpMessageType.MESSAGE);
    if (sessionId != null) headerAccessor.setSessionId(sessionId);
    headerAccessor.setLeaveMutable(true);
    return headerAccessor.getMessageHeaders();
  }
}
