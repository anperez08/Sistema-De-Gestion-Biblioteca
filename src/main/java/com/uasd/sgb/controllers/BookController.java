package com.uasd.sgb.controllers;

import com.uasd.sgb.dto.request.BookDto;
import com.uasd.sgb.dto.response.BookCollectionResponse;
import com.uasd.sgb.dto.response.BookResponseDto;
import com.uasd.sgb.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1.0/sgb/book",produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

    private final BookService bookService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<BookResponseDto> findById(@PathVariable(name = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findById(id));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<BookCollectionResponse> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                          @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAll(page,pageSize));
    }

    @PostMapping(value = "/save")
    public ResponseEntity<BookResponseDto> save(@RequestBody BookDto bookDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(bookDto));
    }

    @PutMapping(value = "/update")
    public ResponseEntity<BookResponseDto> update(@RequestBody BookDto bookDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.update(bookDto));
    }

    @DeleteMapping (value = "/delete/{id}")
    public ResponseEntity<String> delete (@PathVariable(name = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.delete(id));
    }

}
