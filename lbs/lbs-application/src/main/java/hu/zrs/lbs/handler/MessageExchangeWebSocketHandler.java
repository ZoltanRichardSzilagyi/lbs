package hu.zrs.lbs.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MessageExchangeWebSocketHandler extends TextWebSocketHandler {

	private static final Logger logger = LoggerFactory.getLogger(MessageExchangeWebSocketHandler.class);

	@Override
	protected void handleTextMessage(final WebSocketSession session, final TextMessage message) throws Exception {
		logger.info(message.getPayload());
		super.handleTextMessage(session, message);
	}

	@Override
	public void afterConnectionEstablished(final WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
	}

	@Override
	public void handleTransportError(final WebSocketSession session, final Throwable exception) throws Exception {
		super.handleTransportError(session, exception);
	}

	@Override
	public void afterConnectionClosed(final WebSocketSession session, final CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
	}

}
