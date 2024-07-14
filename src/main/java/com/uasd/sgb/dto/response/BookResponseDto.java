package com.uasd.sgb.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
@ToString
public class BookResponseDto {
    private Long id;
    private String title;
    private String author;
    private String ISBN;
    private Long availableQty;
}
