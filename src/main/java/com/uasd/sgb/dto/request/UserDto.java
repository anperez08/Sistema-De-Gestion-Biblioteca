package com.uasd.sgb.dto.request;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class UserDto implements Serializable {
    private Long Id;
    private String name;
    private String address;
    private String contact;
}
