package ru.ikupdev.library.model;

import lombok.*;

import javax.persistence.*;

/**
 * @author Ilya V. Kupriyanov
 * @version 02.01.2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tokenValue;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
