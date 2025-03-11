package com.navy.navy_land_api.service;

import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPOutputStream;


// 데이터 압축 로직
@Service
public class CompressionService {
    public byte[] compress(String data) throws Exception {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        try (GZIPOutputStream gzipStream = new GZIPOutputStream(byteStream)) {
            gzipStream.write(data.getBytes());
        }
        return byteStream.toByteArray();
    }
}