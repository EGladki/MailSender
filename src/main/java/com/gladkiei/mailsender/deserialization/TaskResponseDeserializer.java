package com.gladkiei.mailsender.deserialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gladkiei.mailsender.dtos.TaskResponseDto;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;

public class TaskResponseDeserializer implements Deserializer<TaskResponseDto> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public TaskResponseDto deserialize(String topic, byte[] data) {
        try {
            if (data == null){
                System.out.println("Null received at deserializing");
                return null;
            }
            System.out.println("Deserializing...");
            return objectMapper.readValue(new String(data, StandardCharsets.UTF_8), TaskResponseDto.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to MessageDto");
        }
    }

}

