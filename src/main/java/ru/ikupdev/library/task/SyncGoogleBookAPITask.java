package ru.ikupdev.library.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.ikupdev.library.service.ISyncBookService;

/**
 * @author Ilya V. Kupriyanov
 * @version 21/02/2022
 */
@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@RequiredArgsConstructor
public class SyncGoogleBookAPITask {
    private final ISyncBookService syncBookService;

    @Scheduled(cron = "${library.task.sync-google-book-api.cron:-}")
    public void startSyncTask() {
        try {
            syncBookService.processSync();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

}
