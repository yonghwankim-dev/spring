package jpabook.japshop.controller;

import jpabook.japshop.domain.Book;
import jpabook.japshop.domain.Item;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookForm {
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;

    public Book createBook() {
        Book book = Book.builder()
                        .id(id)
                        .name(name)
                        .price(price)
                        .stockQuantity(stockQuantity)
                        .author(author)
                        .isbn(isbn)
                        .build();

        return book;
    }

    public static BookForm createBookForm(Book item){
        BookForm bookForm = builder()
                            .id(item.getId())
                            .name(item.getName())
                            .price(item.getPrice())
                            .stockQuantity(item.getStockQuantity())
                            .author(item.getAuthor())
                            .isbn(item.getIsbn())
                            .build();

        return bookForm;
    }
}
