package com.navy.navy_land_api.controller;

import com.navy.navy_land_api.service.TransmissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transmission")
public class TransmissionController {

    private final TransmissionService transmissionService;

    public TransmissionController(TransmissionService transmissionService) {
        this.transmissionService = transmissionService;
    }

    // 단일 스레드로 파일 전송 처리
    @PostMapping("/send-single")
    public String sendDataSingle(@RequestBody List<String> fileNames) {
        try {
            return transmissionService.sendDataSingleThread(fileNames);
        } catch (Exception e) {
            return "전송 실패: " + e.getMessage();
        }
    }

    // 멀티 스레드로 파일 전송 처리
    @PostMapping("/send-multiple")
    public String sendDataMultiple(@RequestBody List<String> fileNames) {
        try {
            return transmissionService.sendDataMultiThread(fileNames);
        } catch (Exception e) {
            return "전송 실패: " + e.getMessage();
        }
    }
}