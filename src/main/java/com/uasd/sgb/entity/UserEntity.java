package com.uasd.sgb.entity;

import com.uasd.sgb.dto.request.UserDto;
import com.uasd.sgb.dto.response.UserResponseDto;
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
@Table(name = "tb_user")
public class UserEntity implements TransformFrom<UserDto,UserEntity>, ToDTO<UserResponseDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario",updatable = false)
    private Long id;

    @Column(name = "nombre",updatable = false)
    private String name;

    @Column(name = "direccion")
    private String address;

    @Column(name = "contacto")
    private String contact;

    @Override
    public UserEntity from(UserDto userDto) {
        return UserEntity.builder()
                .name(userDto.getName())
                .address(userDto.getAddress())
                .contact(userDto.getContact())
                .build();
    }



    @Override
    public UserResponseDto toDto() {
        return UserResponseDto.builder()
                .Id(this.id)
                .name(this.name)
                .address(this.address)
                .contact(this.contact)
                .build();
    }
}
