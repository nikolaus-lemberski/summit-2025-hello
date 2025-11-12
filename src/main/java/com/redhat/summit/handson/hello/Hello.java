package com.redhat.summit.handson.hello;

import java.util.Base64;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class Hello {

    @ConfigProperty(name = "hello.key")
    private String helloKey;

    @ConfigProperty(name = "hello.text")
    private String helloText;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        String encrypted_text = encryptDecrypt(helloText);
        String base64_encrypted_text = Base64.getEncoder().encodeToString(encrypted_text.getBytes());
        return base64_encrypted_text.substring(0, 15);
    }

    private String encryptDecrypt(String input) {
        char[] key = helloKey.toCharArray();
        char[] data = input.toCharArray();
        char[] output = new char[data.length];

        for (int i = 0; i < data.length; i++) {
            output[i] = (char) (data[i] ^ key[i % key.length]);
        }
        return new String(output);
    }

}
