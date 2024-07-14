package com.uasd.sgb.entity;

import com.uasd.sgb.dto.request.BookDto;
import com.uasd.sgb.dto.response.BookResponseDto;
import com.uasd.sgb.utils.ToDTO;
import com.uasd.sgb.utils.TransformFrom;
import jakarta.persistence.*;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "tb_book")
public class BookEntity implements TransformFrom<BookDto, BookEntity>, ToDTO<BookResponseDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro",updatable = false)
    private Long id;

    @Column(name = "titulo",updatable = false)
    private String title;

    @Column(name = "autor",updatable = false)
    private String author;

    @Column(name = "isbn",updatable = false)
    private String isbn;

    @Column(name = "cantidad_disponible")
    private Long availableQty;

    @Override
    public BookEntity from(BookDto bookDto) {
        return BookEntity.builder()
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .isbn(bookDto.getIsbn())
                .availableQty(bookDto.getAvailableQty())
                .build();
    }

    @Override
    public BookResponseDto toDto() {
        return BookResponseDto.builder()
                .id(this.id)
                .title(this.title)
                .ISBN(this.isbn)
                .availableQty(this.availableQty)
                .build();
    }
}
