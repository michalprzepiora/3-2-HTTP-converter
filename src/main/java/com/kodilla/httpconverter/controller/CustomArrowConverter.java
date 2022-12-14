package com.kodilla.httpconverter.controller;

import com.kodilla.httpconverter.domain.User;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CustomArrowConverter implements HttpMessageConverter<User> {
    private static final String ARROW_SEPARATOR = " -> ";
    private static final String NAME_FIELD = "name";
    private static final String SURNAME_FIELD = "surname";
    private static final String PHONE_FIELD = "phone";
    private static final String EMAIL_FIELD = "email";

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return clazz.isAssignableFrom(User.class) && mediaType.getSubtype().equals("plain") && mediaType.getType().equals("text");
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return clazz.isAssignableFrom(User.class) && mediaType.getSubtype().equals("plain") && mediaType.getType().equals("text");
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return List.of(MediaType.ALL);
    }

    @Override
    public User read(Class<? extends User> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        Map<String, String> bodyMap = getBodyMap(inputMessage);
        return User.builder()
                .name(bodyMap.get(NAME_FIELD))
                .surname(bodyMap.get(SURNAME_FIELD))
                .phone(Integer.parseInt(bodyMap.get(PHONE_FIELD)))
                .email(bodyMap.get(EMAIL_FIELD))
                .build();
    }

    @Override
    public void write(User user, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

    }

    private Map<String, String> getBodyMap(HttpInputMessage inputMessage) throws IOException {
        InputStream inputStream = inputMessage.getBody();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Map<String, String> result = new HashMap<>();
        while (reader.ready()) {
            String line = reader.readLine();
            String[] splintedLine = line.split(ARROW_SEPARATOR);
            result.put(splintedLine[0], splintedLine[1]);
        }
        inputStream.close();
        reader.close();
        return result;
    }
}
