package ru.ikupdev.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Ilya V. Kupriyanov
 * @version 16.02.2022
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "bookshelf")
public class Bookshelf extends DatedEntity {
    @ApiModelProperty(notes = "Наименование книжной полки", required = true)
    @NotNull
    @Size(max = 512)
    @Column(name = "bookshelf_name")
    private String bookshelfName;
    @ApiModelProperty(notes = "Описание книжной полки")
    @Size(max = 2048)
    @Column(name = "description")
    private String description;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

//    @ManyToMany
//    @JoinTable(
//            name = "user_book",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "book_id")
//    )
//    private List<Book> books;
//
//    public void addBook(Book book) {
//        this.getBooks().add(book);
//    }
//
}
