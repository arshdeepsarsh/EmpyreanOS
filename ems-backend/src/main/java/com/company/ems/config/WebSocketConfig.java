package com.company.ems.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // This is the tunnel door. We allow any frontend to connect via SockJS
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Messages sent to /app go to our Java Controller
        registry.setApplicationDestinationPrefixes("/app");
        // Messages sent to /topic are broadcasted to everyone listening
        registry.enableSimpleBroker("/topic");
    }
}