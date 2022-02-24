package ru.ikupdev.library.service.impl;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.ikupdev.library.converter.GBookToBookConverter;
import ru.ikupdev.library.feign.GoogleBooksApiClient;
import ru.ikupdev.library.model.Book;
import ru.ikupdev.library.repository.BookRepository;
import ru.ikupdev.library.service.IBookService;
import ru.ikupdev.library.service.ISyncBookService;
import ru.ikupdev.library.util.FeignUtil;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Ilya V. Kupriyanov
 * @version 21.02.2022
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class SyncBookService implements ISyncBookService {
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle(SyncBookService.class.getName());
    private final IBookService bookService;
    private final GoogleBooksApiClient booksApiClient;
    private final BookRepository bookRepository;
    private final GBookToBookConverter converter;
    Long updatedCounter = 0L;

    @Value("${library.task.sync-google-book-api.batch-size}")
    private Integer batchSize;

    @Override
    public void processSync() {
        long count = bookRepository.count();
        int pageCount = (int) Math.ceil((double) count / batchSize);
        int pageNumber = 0;
        if (pageCount == 0) {
            log.info(BUNDLE.getString("sync.books.process.no.books.for.processing"));
            return;
        }
        while (pageNumber < pageCount) {
            Page<Book> booksBatch = bookRepository.findAll(PageRequest.of(pageNumber, batchSize));
            log.info(BUNDLE.getString("sync.books.process.batch.processing"), pageNumber);
            processBatch(booksBatch.getContent());
            pageNumber++;
        }
        log.info(BUNDLE.getString("sync.books.process.finished.success"), updatedCounter);
        updatedCounter = 0L;
    }

    private void processBatch(List<Book> batch) {
        for (Book book : batch) {
            try {
                Book newBook = converter.convertGBookItemToBookEntity(FeignUtil.feignLog(() -> booksApiClient.getBookByVolumeId(book.getVolumeId())));
                if (!book.equals(newBook)) {
                    newBook.setId(book.getId());
                    newBook.setCreated(book.getCreated());
                    newBook.setUpdated(new Date());
                    bookService.saveBook(newBook);
                    updatedCounter++;
                    log.info(BUNDLE.getString("sync.books.process.book.updated"), book.getId());
                }
            } catch (FeignException e) {
                log.error(e.getMessage());
            }
        }
    }

}
