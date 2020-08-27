package com.code.demo.domain;

import com.code.demo.domain.converters.TokenTypeConverter;
import com.code.demo.domain.enums.TokenType;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "TOKENS")
@Data
public class Token {

    @Id
    private String id;

    @NotBlank
    @Column(name = "USER_ID")
    private String userId;

    @Convert(converter = TokenTypeConverter.class)
    @Column(name = "TOKEN_TYPE")
    private TokenType type;

    private LocalDateTime expiration;

}
