package ru.ikupdev.library.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.ikupdev.library.model.Book;
import java.util.Optional;

/**
 * @author Ilya V. Kupriyanov
 * @version 18.01.2022
 */
@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long>, QuerydslPredicateExecutor<Book> {
    Optional<Book> findByVolumeId(String volumeId);
}
