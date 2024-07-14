package com.uasd.sgb.dto.response;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class LoanCollectionResponse {
    private Integer page;
    private Integer pageSize;
    private Integer totalPages;
    private Long totalElements;
    private List<LoanResponseDto> loanResponseDtoList;
}