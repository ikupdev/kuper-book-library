package ru.ikupdev.library.converter;

import org.springframework.stereotype.Component;
import ru.ikupdev.library.model.Book;
import ru.ikupdev.library.model.gbook.*;

import java.util.Date;
import java.util.Optional;

/**
 * @author Ilya V. Kupriyanov
 * @version 12.02.2022
 */
@Component
public class GBookToBookConverter {
    public Book convertGBookItemToBookEntity(GBookItem item) {
        Book book = new Book();
        book.setVolumeId(Optional.of(item.getId()).orElseThrow());
        GVolumeInfo vi = Optional.of(item.getVolumeInfo()).orElseThrow();
        book.setTitle(Optional.of(vi.getTitle()).orElseThrow());
        book.setSubtitle(vi.getSubtitle());
        book.setAuthors(vi.getAuthors() != null && vi.getAuthors().size() > 0 ?
                String.join(", ", vi.getAuthors()) : null);
        book.setDescription(vi.getDescription());
        book.setPageCount(vi.getPageCount());
        book.setLanguage(vi.getLanguage());
        book.setImageLink(vi.getImageLinks() == null ? null : vi.getImageLinks().getThumbnail());
        book.setPreviewLink(vi.getPreviewLink());
        book.setInfoLink(vi.getInfoLink());
        book.setCanonicalVolumeLink(vi.getCanonicalVolumeLink());
        book.setSearchInfo(item.getSearchInfo() == null ? null : item.getSearchInfo().getTextSnippet());
        book.setBuyLink(item.getSaleInfo() == null ? null : item.getSaleInfo().getBuyLink());
        book.setEpubDownloadLink(item.getAccessInfo() == null ? null : item.getAccessInfo().getEpub().getDownloadLink());
        book.setPdfDownloadLink(item.getAccessInfo() == null ? null : item.getAccessInfo().getPdf().getDownloadLink());
        book.setCreated(new Date());
        book.setUpdated(new Date());
        return book;
    }

}
