package org.nng.swdoc.library.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Encoder {
    private static Encoder instance;
    private final MessageDigest md5encoder;

    private Encoder() {
        try {
            md5encoder = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static Encoder getInstance() {
        if (instance == null) {
            instance = new Encoder();
        }
        return instance;
    }

    public String encode(String data) {
        return Base64.getEncoder().encodeToString(md5encoder.digest(data.getBytes(StandardCharsets.UTF_8)));
    }
}


