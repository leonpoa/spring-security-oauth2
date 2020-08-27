package com.code.demo.domain.converters;

import com.code.demo.domain.enums.UserStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static java.util.Objects.nonNull;

@Converter
public class UserStatusConverter implements AttributeConverter<UserStatus, String> {

    @Override
    public String convertToDatabaseColumn(UserStatus attribute) {
        return nonNull(attribute) ? attribute.getValue() : null;
    }

    @Override
    public UserStatus convertToEntityAttribute(String column) {
        return nonNull(column) ? UserStatus.from(column) : null;
    }

}
