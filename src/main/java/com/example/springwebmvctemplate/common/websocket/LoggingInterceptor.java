package com.example.springwebmvctemplate.common.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoggingInterceptor implements ChannelInterceptor {
  @Override
  public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
    StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);
    String sessionId = headerAccessor.getSessionId();
    if (StompCommand.CONNECT.equals(headerAccessor.getCommand())) {
      log.info("######### " + sessionId + " is connected successfully");
    } else if (StompCommand.DISCONNECT.equals(headerAccessor.getCommand())) {
      log.info("######### " + sessionId + " is disconnected successfully");
    }
  }
}
