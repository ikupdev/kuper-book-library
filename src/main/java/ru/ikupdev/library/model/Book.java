package ru.ikupdev.library.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Ilya V. Kupriyanov
 * @version 11.12.2021
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "book")
public class Book extends DatedEntity {
    @ApiModelProperty(notes = "volumeId книги в реестре Google Book API", required = true)
    @NotNull
    @Column(name = "volume_id")
    @Size(max = 64)
    private String volumeId;
    @ApiModelProperty(notes = "Название")
    @Column(name = "title")
    @Size(max = 1024)
    private String title;
    @ApiModelProperty(notes = "Подзаголовок")
    @Size(max = 2048)
    @Column(name = "subtitle")
    private String subtitle;
    @ApiModelProperty(notes = "Описание")
    @Size(max = 4096)
    @Column(name = "description")
    private String description;
    @ApiModelProperty(notes = "Кол-во страниц")
    @Column(name = "page_count")
    private Integer pageCount;
    @ApiModelProperty(notes = "Язык")
    @Size(max = 64)
    @Column(name = "language")
    private String language;
    @ApiModelProperty(notes = "Ссылка на изображение")
    @Size(max = 4096)
    @Column(name = "image_link")
    private String imageLink;
    @ApiModelProperty(notes = "Ссылка на предпросмотр")
    @Size(max = 4096)
    @Column(name = "preview_link")
    private String previewLink;
    @ApiModelProperty(notes = "Ссылка на информацию о книге")
    @Size(max = 4096)
    @Column(name = "info_link")
    private String infoLink;
    @ApiModelProperty(notes = "Основная ссылка на книгу")
    @Size(max = 4096)
    @Column(name = "canonical_volume_link")
    private String canonicalVolumeLink;
    @ApiModelProperty(notes = "Авторы книги")
    @Size(max = 4096)
    @Column(name = "authors")
    private String authors;
    @ApiModelProperty(notes = "Информация для поиска")
    @Size(max = 4096)
    @Column(name = "search_info")
    private String searchInfo;
    @ApiModelProperty(notes = "Ссылка на скачивание книги в формате epub")
    @Size(max = 4096)
    @Column(name = "epub_download_link")
    private String epubDownloadLink;
    @ApiModelProperty(notes = "Ссылка на скачивание книги в формате pdf")
    @Size(max = 4096)
    @Column(name = "pdf_download_link")
    private String pdfDownloadLink;
    @ApiModelProperty(notes = "Ссылка на страницу чтения книги онлайн")
    @Size(max = 4096)
    @Column(name = "web_reader_link")
    private String webReaderLink;
    @ApiModelProperty(notes = "Ссылка на страницу покупки книги")
    @Size(max = 4096)
    @Column(name = "buy_link")
    private String buyLink;
    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
    private Set<Bookshelf> bookshelfs = new HashSet<>();
}