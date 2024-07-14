package com.uasd.sgb.controllers;

import com.uasd.sgb.dto.request.LoanDto;
import com.uasd.sgb.dto.response.LoanCollectionResponse;
import com.uasd.sgb.dto.response.LoanResponseDto;
import com.uasd.sgb.services.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1.0/sgb/loan",produces = MediaType.APPLICATION_JSON_VALUE)
public class LoanController {

    private final LoanService loanService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<LoanResponseDto> findById(@PathVariable(name = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(loanService.findById(id));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<LoanCollectionResponse> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                          @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize){
        return ResponseEntity.status(HttpStatus.OK).body(loanService.findAll(page,pageSize));
    }

    @GetMapping(value = "/fine/{id}")
    public ResponseEntity<String> findFine(@PathVariable(name = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(loanService.fineLoan(id));
    }


    @PostMapping(value = "/save")
    public ResponseEntity<LoanResponseDto> save(@RequestBody LoanDto loanDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(loanService.save(loanDto));
    }

    @PutMapping(value = "/update")
    public ResponseEntity<LoanResponseDto> update(@RequestBody LoanDto loanDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(loanService.update(loanDto));
    }



}
