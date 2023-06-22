package com.example.enums.converters;

import com.example.enums.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {
    @Override
    public String convertToDatabaseColumn(Status status) {
        if (status == null) {
            return null;
        }
        return status.getValue();
    }

    @Override
    public Status convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        return Stream.of(Status.values())
                .filter(c -> c.getValue().equals(s))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
