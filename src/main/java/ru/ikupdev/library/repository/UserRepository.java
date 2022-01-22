package ru.ikupdev.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ikupdev.library.model.User;

import java.util.List;
import java.util.Optional;

/**
 * @author Ilya V. Kupriyanov
 * @version 23/04/2021
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByLogin(String login);

}
