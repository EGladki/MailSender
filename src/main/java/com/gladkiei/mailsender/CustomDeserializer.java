package com.gladkiei.mailsender;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gladkiei.mailsender.dtos.UserResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;


@Slf4j
public class CustomDeserializer implements Deserializer<UserResponseDto> {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public UserResponseDto deserialize(String topic, byte[] data) {
        try {
            if (data == null){
                System.out.println("Null received at deserializing");
                return null;
            }
            System.out.println("Deserializing...");
            return objectMapper.readValue(new String(data, "UTF-8"), UserResponseDto.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to MessageDto");
        }
    }

}
