package ru.ikupdev.library.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Ilya V. Kupriyanov
 * @version 11.12.2021
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "book")
public class Book extends DatedEntity {
    @Column(name = "volume_id")
    private String volumeId;
    @Column(name = "title")
    private String title;
    @Column(name = "subtitle")
    private String subtitle;
    @Column(name = "description")
    private String description;
    @Column(name = "page_count")
    private Integer pageCount;
    @Column(name = "language")
    private String language;
    @Column(name = "image_link")
    private String imageLink;
    @Column(name = "preview_link")
    private String previewLink;
    @Column(name = "info_link")
    private String infoLink;
    @Column(name = "canonical_volume_link")
    private String canonicalVolumeLink;
    @Column(name = "authors")
    private String authors;
    @Column(name = "search_info")
    private String searchInfos;
    @Column(name = "epub_download_link")
    private String epubDownloadLink;
    @Column(name = "pdf_download_link")
    private String pdfDownloadLink;
    @Column(name = "web_reader_link")
    private String webReaderLink;
    @Column(name = "buy_link")
    private String buyLink;
}
