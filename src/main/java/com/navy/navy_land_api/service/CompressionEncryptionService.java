package com.navy.navy_land_api.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CompressionEncryptionService {

    // 데이터 압축
    public byte[] compressData(byte[] data) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream)) {
            gzipOutputStream.write(data);
        }
        return byteArrayOutputStream.toByteArray();  // 압축된 데이터 반환
    }

    // 데이터 암호화 (SHA-256)
    public byte[] encryptData(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(data);  // SHA-256 해시 값으로 암호화
    }

    // 압축하고 암호화하는 메서드
    public byte[] compressAndEncryptData(byte[] data) throws IOException, NoSuchAlgorithmException {
        // 데이터 압축
        byte[] compressedData = compressData(data);

        // 압축된 데이터 암호화
        return encryptData(compressedData);
    }
}