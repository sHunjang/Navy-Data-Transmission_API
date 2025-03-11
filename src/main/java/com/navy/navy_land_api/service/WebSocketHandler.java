package com.navy.navy_land_api.service;

import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws InterruptedException, IOException {
        // 클라이언트에서 전송한 메시지 처리
        String clientMessage = message.getPayload();
        System.out.println("클라이언트에서 받은 메시지: " + clientMessage);

        // 클라이언트로 응답 전송
        String serverResponse = "서버가 메시지를 받았어요: " + clientMessage;
        session.sendMessage(new TextMessage(serverResponse));
    }
}