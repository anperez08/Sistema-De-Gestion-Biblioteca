package com.uasd.sgb.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
@ToString
public class LoanResponseDto {
    private Long id;
    private BookResponseDto bookResponseDto;
    private UserResponseDto userResponseDto;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private Long  returnFine;
}
