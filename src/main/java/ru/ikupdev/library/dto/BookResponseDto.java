package ru.ikupdev.library.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

/**
 * @author Ilya V. Kupriyanov
 * @version 23.02.2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDto {
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "UTC")
    private Date created;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "UTC")
    private Date updated;
    private String volumeId;
    private String title;
    private String subtitle;
    private String description;
    private Integer pageCount;
    private String language;
    private String imageLink;
    private String previewLink;
    private String infoLink;
    private String canonicalVolumeLink;
    private String authors;
    private String searchInfo;
    private String epubDownloadLink;
    private String pdfDownloadLink;
    private String webReaderLink;
    private String buyLink;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookResponseDto book = (BookResponseDto) o;
        return volumeId.equals(book.volumeId) &&
                Objects.equals(title, book.title) &&
                Objects.equals(subtitle, book.subtitle) &&
                Objects.equals(description, book.description) &&
                Objects.equals(pageCount, book.pageCount) &&
                Objects.equals(language, book.language) &&
                Objects.equals(previewLink, book.previewLink) &&
                Objects.equals(infoLink, book.infoLink) &&
                Objects.equals(canonicalVolumeLink, book.canonicalVolumeLink) &&
                Objects.equals(authors, book.authors) &&
                Objects.equals(searchInfo, book.searchInfo) &&
                Objects.equals(epubDownloadLink, book.epubDownloadLink) &&
                Objects.equals(pdfDownloadLink, book.pdfDownloadLink) &&
                Objects.equals(webReaderLink, book.webReaderLink) &&
                Objects.equals(buyLink, book.buyLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(volumeId, title, subtitle, description, pageCount, language, imageLink, previewLink, infoLink, canonicalVolumeLink, authors, searchInfo, epubDownloadLink, pdfDownloadLink, webReaderLink, buyLink);
    }
}