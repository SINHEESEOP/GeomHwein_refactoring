package com.geomhwein.go.websocket;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class EchoHandler extends TextWebSocketHandler {

	 HashMap<String, WebSocketSession> sessionMap = new HashMap<>();
	 
	 //실행시
	 @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionMap.put(session.getId(), session);
    }
	
	 //종료시
	@Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionMap.remove(session.getId());
    }
	
	// 특정 사용자에게 웹소켓 메시지를 전송하는 메소드
    public void sendNotification(String username, String message) throws Exception {
        // 세션맵에서 사용자의 웹소켓 세션을 얻어냄
        WebSocketSession session = sessionMap.get(username);
        // 해당 세션이 존재하고 열려있는 경우, 메시지를 전송
        if (session != null && session.isOpen()) {
            session.sendMessage(new TextMessage(message));
        }
    }
    
 // 웹소켓 세션의 URI를 파싱하여 사용자 id를 찾아내는 메소드
    public String searchUserName(WebSocketSession session) {
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(session.getUri().toString()).build();
        return uriComponents.getQueryParams().getFirst("uid");
    }
 
}
