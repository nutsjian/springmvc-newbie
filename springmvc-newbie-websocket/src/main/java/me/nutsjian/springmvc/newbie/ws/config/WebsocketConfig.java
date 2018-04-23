package me.nutsjian.springmvc.newbie.ws.config;

import me.nutsjian.springmvc.newbie.ws.handler.WsCoreHandler;
import me.nutsjian.springmvc.newbie.ws.interceptor.WebsocketHandshakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebMvcConfigurer, WebSocketConfigurer {

    private final WsCoreHandler wsCoreHandler;
    private final WebsocketHandshakeInterceptor websocketHandshakeInterceptor;

    @Autowired
    public WebsocketConfig(WsCoreHandler wsCoreHandler, WebsocketHandshakeInterceptor websocketHandshakeInterceptor) {
        this.wsCoreHandler = wsCoreHandler;
        this.websocketHandshakeInterceptor = websocketHandshakeInterceptor;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(wsCoreHandler, "/ws-wmbi")
                .addInterceptors(websocketHandshakeInterceptor)
                .setAllowedOrigins("http://localhost");


    }
}
