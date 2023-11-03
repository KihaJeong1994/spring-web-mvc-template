package com.example.springwebmvctemplate.common.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
  private final LoggingInterceptor loggingInterceptor;

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry
        .addEndpoint("/connect")
        .setAllowedOriginPatterns("*") // be added to test on web
        .withSockJS(); // HTTP URL for endpoint to which a WebSocket client needs to connect for the
    // WebSocket handshake
  }

  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
    config.setApplicationDestinationPrefixes(
        "/app"); // STOMP messages whose destination header begins with /app are routed to
    // @MessageMapping methods in @Controller
    config.setUserDestinationPrefix("/user");
    config.enableSimpleBroker(
        "/topic",
        "/queue"); // use the built-in message broker for subscriptions and broadcasting whose
    // destination header begins with /topic , /queue
  }

  @Override
  public void configureClientInboundChannel(ChannelRegistration registration) {
    registration.interceptors(loggingInterceptor);
  }
}
