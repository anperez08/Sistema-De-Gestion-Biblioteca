package com.uasd.sgb.dto.request;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class BookDto implements Serializable {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private Long availableQty;
}
