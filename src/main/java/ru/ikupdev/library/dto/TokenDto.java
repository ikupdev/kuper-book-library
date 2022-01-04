package ru.ikupdev.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ikupdev.library.model.Token;

/**
 * @author Ilya V. Kupriyanov
 * @version 02.01.2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
    private String value;
    public static TokenDto from(Token token) {
        return new TokenDto(token.getTokenValue());
    }
}
