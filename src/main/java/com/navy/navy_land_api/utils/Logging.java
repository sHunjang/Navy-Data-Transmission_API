package com.navy.navy_land_api.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class Logging {
    private static final Logger logger = LoggerFactory.getLogger(Logging.class);
    private static final String LOG_FILE = "logs/data-transmission.log";

    public void logMessage(String message) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String logEntry = "[" + timestamp + "] " + message;

        System.out.println(logEntry);

        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(logEntry + "\n");
        } catch (IOException e) {
            logger.error("로그 파일 저장 오류", e);
        }
    }
}