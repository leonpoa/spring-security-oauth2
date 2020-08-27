package com.code.demo.repository;

import com.code.demo.domain.Token;
import com.code.demo.domain.enums.TokenType;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface TokenRepository extends PagingAndSortingRepository<Token, String> {

    Optional<Token> findByIdAndType(String id, TokenType type);

}
