package ru.ikupdev.library.model.gbook;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @author Ilya V. Kupriyanov
 * @version 07.02.2022
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GVolumeInfo {
    private String title;
    private List<String> authors;
    private String publisher;
    private String publishedDate;
    private String description;
    private List<IndustryIdentifier> industryIdentifiers;
    private Long pageCount;
    private String printType;
    private List<String> categories;
    private String maturityRating;
    private ImageLinks imageLinks;
    private String language;
    private String previewLink;
    private String infoLink;
    private String canonicalVolumeLink;
}
