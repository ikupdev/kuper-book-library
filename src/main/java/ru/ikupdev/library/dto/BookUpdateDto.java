package ru.ikupdev.library.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import javax.validation.constraints.Size;

/**
 * @author Ilya V. Kupriyanov
 * @version 11.12.2021
 */
@Data
@NoArgsConstructor
public class BookUpdateDto {
    @ApiModelProperty(notes = "volumeId книги в реестре Google Book API")
    @Size(max = 64)
    private String volumeId;
    @ApiModelProperty(notes = "Название")
    @Size(max = 1024)
    private String title;
    @ApiModelProperty(notes = "Подзаголовок")
    @Size(max = 2048)
    private String subtitle;
    @ApiModelProperty(notes = "Описание")
    @Size(max = 4096)
    private String description;
    @ApiModelProperty(notes = "Кол-во страниц")
    private Integer pageCount;
    @ApiModelProperty(notes = "Язык")
    @Size(max = 64)
    private String language;
    @ApiModelProperty(notes = "Ссылка на изображение")
    @Size(max = 4096)
    private String imageLink;
    @ApiModelProperty(notes = "Ссылка на предпросмотр")
    @Size(max = 4096)
    private String previewLink;
    @ApiModelProperty(notes = "Ссылка на информацию о книге")
    @Size(max = 4096)
    private String infoLink;
    @ApiModelProperty(notes = "Основная ссылка на книгу")
    @Size(max = 4096)
    private String canonicalVolumeLink;
    @ApiModelProperty(notes = "Авторы книги")
    @Size(max = 1024)
    private String authors;
    @ApiModelProperty(notes = "Информация для поиска")
    @Size(max = 4096)
    private String searchInfo;
    @ApiModelProperty(notes = "Ссылка на скачивание книги в формате epub")
    @Size(max = 4096)
    private String epubDownloadLink;
    @ApiModelProperty(notes = "Ссылка на скачивание книги в формате pdf")
    @Size(max = 4096)
    private String pdfDownloadLink;
    @ApiModelProperty(notes = "Ссылка на страницу чтения книги онлайн")
    @Size(max = 4096)
    private String webReaderLink;
    @ApiModelProperty(notes = "Ссылка на страницу покупки книги")
    @Size(max = 4096)
    private String buyLink;
}