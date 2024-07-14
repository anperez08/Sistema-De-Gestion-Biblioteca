package com.uasd.sgb.services;

import com.uasd.sgb.dto.request.UserDto;
import com.uasd.sgb.dto.response.UserCollectionResponse;
import com.uasd.sgb.dto.response.UserResponseDto;
import com.uasd.sgb.entity.UserEntity;
import com.uasd.sgb.repository.UserRepository;
import com.uasd.sgb.utils.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto findById(Long id){
        return  userRepository.findById(id).orElseThrow().toDto();
    }

    public UserCollectionResponse findAll(Integer page, Integer pageSize){
        Page<UserEntity> entityPage = userRepository.findAll(PageRequest.of(page,pageSize, Sort.by("id").descending()));
        return UserCollectionResponse.builder()
                .page(page)
                .pageSize(pageSize)
                .totalPages(entityPage.getTotalPages()-1)
                .totalElements(entityPage.getTotalElements())
                .userResponseDtoList(entityPage.stream().map(UserEntity::toDto).toList())
                .build();
    }

    @Transactional
    public UserResponseDto save(UserDto userDto){
        UserEntity userEntity = UserEntity.builder().build().from(userDto);
        return userRepository.save(userEntity).toDto();
    }

    @Transactional
    public UserResponseDto update(UserDto userDto){
        UserResponseDto userResponseDto = findById(userDto.getId());
        if(userResponseDto == null){
            throw new NotFoundException("El usuario no existe");
        }else{
            UserEntity userEntity = new UserEntity();
            userEntity.setId(userDto.getId());
            userEntity.setName(userDto.getName());
            userEntity.setAddress(userDto.getAddress());
            userEntity.setContact(userDto.getContact());
            return userRepository.save(userEntity).toDto();
        }
    }

    @Transactional
    public String delete(Long id){
        if(findById(id)== null){
            throw  new NotFoundException("El usuario no exitste");
        }else{
            userRepository.deleteById(id);
            return "Usuario eliminado existosamente";
        }
    }

}
