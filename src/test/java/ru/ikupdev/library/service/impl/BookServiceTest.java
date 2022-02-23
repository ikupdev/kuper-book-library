package ru.ikupdev.library.service.impl;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.ikupdev.library.AbstractIntegrationTest;

/**
 * @author Ilya V. Kupriyanov
 * @version 22.02.2022
 */
class BookServiceTest extends AbstractIntegrationTest {

    @Test
    void testDeleteOrphanBooks() {
        Assert.assertEquals(1, components.getBookRepository().findOrphanBookIds().size());
        components.getBookService().deleteOrphanBooks();
        Assert.assertEquals(0, components.getBookRepository().findOrphanBookIds().size());
    }
}