package com.code.demo.domain.converters;

import com.code.demo.domain.enums.TokenType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static java.util.Objects.nonNull;

@Converter
public class TokenTypeConverter implements AttributeConverter<TokenType, String> {

    @Override
    public String convertToDatabaseColumn(TokenType attribute) {
        return nonNull(attribute) ? attribute.getValue() : null;
    }

    @Override
    public TokenType convertToEntityAttribute(String column) {
        return nonNull(column) ? TokenType.from(column) : null;
    }

}
