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
public class UserResponseDto {
    private Long Id;
    private String name;
    private String address;
    private String contact;
}
