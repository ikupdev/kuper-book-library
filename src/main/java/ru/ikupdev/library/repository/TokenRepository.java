package ru.ikupdev.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ikupdev.library.model.Token;

import java.util.Optional;

/**
 * @author Ilya V. Kupriyanov
 * @version 02.01.2022
 */
@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findTokenByTokenValue(String tokenValue);
}
