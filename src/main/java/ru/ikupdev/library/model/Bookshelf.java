package ru.ikupdev.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Ilya V. Kupriyanov
 * @version 16.02.2022
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@ToString(of = {"name", "description"})
@NoArgsConstructor
@Entity
@Table(name = "bookshelf")
public class Bookshelf extends DatedEntity {
    @ApiModelProperty(notes = "Наименование книжной полки", required = true)
    @NotNull
    @Size(max = 512)
    @Column(name = "name")
    private String name;
    @ApiModelProperty(notes = "Описание книжной полки")
    @Size(max = 2048)
    @Column(name = "description")
    private String description;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "bookshelf_book",
            joinColumns = {@JoinColumn(name = "bookshelf_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id")}
    )
    private Set<Book> books = new HashSet<>();

    public void addBook(Book book) {
        this.books.add(book);
        book.getBookshelfs().add(this);
    }

    public void removeBook(Book book) {
        this.books.remove(book);
        book.getBookshelfs().remove(this);
    }

}
