package com.navy.navy_land_api.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/send")  // 클라이언트에서 "/app/send"로 보내는 메시지 처리
    @SendTo("/topic/messages")  // "/topic/messages"를 구독한 클라이언트에게 전송
    public String sendMessage(String message) {
        // 처리된 메시지를 클라이언트로 반환
        return "서버에서 받은 메시지: " + message;
    }
}