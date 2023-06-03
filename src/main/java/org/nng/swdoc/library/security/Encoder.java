package org.nng.swdoc.library.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Encoder {
    public static String encode(String data) {
        try {
            var md5encoder = MessageDigest.getInstance("MD5");
            return Base64.getEncoder().encodeToString(md5encoder.digest(data.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}


