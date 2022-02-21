package ru.ikupdev.library.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.ikupdev.library.model.Bookshelf;

import java.util.Optional;

/**
 * @author Ilya V. Kupriyanov
 * @version 16.02.2022
 */
@Repository
public interface BookshelfRepository extends PagingAndSortingRepository<Bookshelf, Long>, QuerydslPredicateExecutor<Bookshelf> {
    Optional<Bookshelf> findByName(String name);
}
