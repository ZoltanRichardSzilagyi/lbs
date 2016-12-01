package hu.zrs.lbs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.ExceptionWebSocketHandlerDecorator;

import hu.zrs.lbs.handler.MessageExchangeWebSocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(final WebSocketHandlerRegistry registry) {
		registry.addHandler(messageExchangeWebSocketHandler(), "/api");
	}

	@Bean
	public WebSocketHandler messageExchangeWebSocketHandler() {
		return new ExceptionWebSocketHandlerDecorator(new MessageExchangeWebSocketHandler());
	}


}
