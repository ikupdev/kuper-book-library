package ru.ikupdev.library.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.ikupdev.library.model.Book;

import java.util.List;
import java.util.Optional;

/**
 * @author Ilya V. Kupriyanov
 * @version 18.01.2022
 */
@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long>, QuerydslPredicateExecutor<Book> {

    Optional<Book> findByVolumeId(String volumeId);

    @Query(value = "select b.id from com.Book as b LEFT OUTER JOIN com.bookshelf_book bb ON b.id = bb.book_id WHERE bb.book_id is NULL",
            nativeQuery = true)
    List<Long> findOrphanBookIds();
}
