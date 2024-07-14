package com.uasd.sgb.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ExceptionResponse {
    private String code;
    private String message;
}
