package com.navy.navy_land_api.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

@Service
public class TcpServer {
    private static final Logger logger = LoggerFactory.getLogger(TcpServer.class);
    // 클라이언트에서 연결할 포트
    private static final int PORT = 5000;

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            logger.info("TCP 서버 시작, 포트: {}", PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                logger.info("클라이언트 연결됨: {}", clientSocket.getInetAddress());

                try (InputStream inputStream = clientSocket.getInputStream()) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        logger.info("데이터 수신: {} bytes", bytesRead);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("TCP 서버 오류", e);
        }
    }
}