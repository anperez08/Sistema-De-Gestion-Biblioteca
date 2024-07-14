package com.uasd.sgb.controllers;

import com.uasd.sgb.dto.request.UserDto;
import com.uasd.sgb.dto.response.UserCollectionResponse;
import com.uasd.sgb.dto.response.UserResponseDto;
import com.uasd.sgb.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1.0/sgb/user",produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable(name = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<UserCollectionResponse> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                          @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll(page,pageSize));
    }

    @PostMapping(value = "/save")
    public ResponseEntity<UserResponseDto> save(@RequestBody UserDto userDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userDto));
    }

    @PutMapping(value = "/update")
    public ResponseEntity<UserResponseDto> update(@RequestBody UserDto userDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.update(userDto));
    }


}
