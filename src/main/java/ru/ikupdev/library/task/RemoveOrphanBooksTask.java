package ru.ikupdev.library.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import ru.ikupdev.library.service.impl.BookService;

import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * @author Ilya V. Kupriyanov
 * @version 21/02/2022
 */
@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@RequiredArgsConstructor
public class RemoveOrphanBooksTask {
    private final ResourceBundle BUNDLE = ResourceBundle.getBundle(RemoveOrphanBooksTask.class.getName());
    private final BookService bookService;

    @Scheduled(cron = "${library.task.remove-orphan-books.cron:-}")
    public void startRemoveOrphanBooksTask() {
        try {
            List<Long> removedIds = bookService.deleteOrphanBooks();
            if (CollectionUtils.isEmpty(removedIds)) {
                log.info(BUNDLE.getString("no.orphan.find"));
            } else {
                String idsStr = removedIds.stream().map(String::valueOf).collect(Collectors.joining(","));
                log.info(BUNDLE.getString("orphan.removed"), removedIds.size(), idsStr);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

}

