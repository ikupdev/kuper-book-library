package ru.ikupdev.library.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.ikupdev.library.model.User;

import java.util.Optional;

/**
 * @author Ilya V. Kupriyanov
 * @version 18.01.2022
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>, QuerydslPredicateExecutor<User> {
    Optional<User> findByLogin(String name);

    Optional<User> findByEmail(String email);
}
