package com.navy.navy_land_api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class TransmissionService {

    private static final Logger logger = LoggerFactory.getLogger(TransmissionService.class);

    // 단일 스레드로 파일 처리
    public String sendDataSingleThread(List<String> fileNames) {
        long startTime = System.currentTimeMillis();  // 시작 시간 기록

        try {
            // 데이터 전송 처리 로직
            for (String fileName : fileNames) {
                processFile(fileName);  // 파일 처리 (읽기, 암호화, 전송)
            }

            long endTime = System.currentTimeMillis();  // 종료 시간 기록
            long duration = endTime - startTime;  // 소요 시간 계산
            logger.info("단일 스레드 처리 시간: " + duration + "ms");
            return "단일 스레드 처리 완료, 시간: " + duration + "ms";
        } catch (Exception e) {
            logger.error("단일 스레드 처리 중 오류 발생: ", e);
            return "전송 실패: " + e.getMessage();
        }
    }

    // 멀티 스레드로 파일 처리
    public String sendDataMultiThread(List<String> fileNames) {
        long startTime = System.currentTimeMillis();  // 시작 시간 기록

        try {
            ExecutorService executor = Executors.newFixedThreadPool(10);  // 10개의 스레드 풀
            for (String fileName : fileNames) {
                executor.submit(() -> processFile(fileName));  // 각 파일을 처리할 작업을 스레드 풀에 제출
            }

            executor.shutdown();  // 모든 작업을 제출 후 종료
            while (!executor.isTerminated()) {
                // 모든 작업이 끝날 때까지 기다림
            }

            long endTime = System.currentTimeMillis();  // 종료 시간 기록
            long duration = endTime - startTime;  // 소요 시간 계산
            logger.info("멀티 스레드 처리 시간: " + duration + "ms");
            return "멀티 스레드 처리 완료, 시간: " + duration + "ms";
        } catch (Exception e) {
            logger.error("멀티 스레드 처리 중 오류 발생: ", e);
            return "전송 실패: " + e.getMessage();
        }
    }

    // 파일 처리 로직 (예: 읽기, 암호화, 전송)
    private void processFile(String fileName) {
        try {
            // 여기에서 파일을 처리하는 실제 로직을 추가합니다.
            // 예를 들어, 파일 읽기, 암호화, 전송 등을 할 수 있습니다.
            logger.info("파일 처리 중: " + fileName);
            Thread.sleep(100);  // 파일 처리 시간을 시뮬레이션 (예시로 100ms)
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("파일 처리 중 오류 발생: ", e);
        }
    }
}