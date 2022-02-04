package ru.ikupdev.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ikupdev.library.model.User;

import java.util.Optional;

/**
 * @author Ilya V. Kupriyanov
 * @version 18.01.2022
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String name);

    Optional<User> findByEmail(String email);
}
