package com.uasd.sgb.services;

import com.uasd.sgb.dto.request.LoanDto;
import com.uasd.sgb.dto.response.LoanCollectionResponse;
import com.uasd.sgb.dto.response.LoanResponseDto;
import com.uasd.sgb.entity.BookEntity;
import com.uasd.sgb.entity.LoanEntity;
import com.uasd.sgb.entity.UserEntity;
import com.uasd.sgb.repository.LoanRepository;
import com.uasd.sgb.utils.NotLoanException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Period;

@RequiredArgsConstructor
@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final UserService userService;
    private final BookService bookService;

    public LoanResponseDto findById(Long id){
        return  loanRepository.findById(id).orElseThrow().toDto();
    }

    public LoanCollectionResponse findAll(Integer page, Integer pageSize){
        Page<LoanEntity> entityPage = loanRepository.findAll(PageRequest.of(page,pageSize, Sort.by("id").descending()));
        return LoanCollectionResponse.builder()
                .page(page)
                .pageSize(pageSize)
                .totalPages(entityPage.getTotalPages()-1)
                .totalElements(entityPage.getTotalElements())
                .loanResponseDtoList(entityPage.stream().map(LoanEntity::toDto).toList())
                .build();
    }

    @Transactional
    public LoanResponseDto save(LoanDto loanDto){
        if(userService.findById(loanDto.getUserId()) == null){
            throw new RuntimeException("El usuario no existe");
        }
        if(bookService.findById(loanDto.getBookId())== null){
            throw new RuntimeException("El libro no existe");
        }
        else{
            if(loanRepository.countByUserEntity_Id(loanDto.getUserId()) >=5){
                throw  new NotLoanException("El usuario ha excedido el limite de prestamos permitidos");
            }
            if(bookService.findById(loanDto.getBookId()).getAvailableQty() == 0)
            {
                throw  new NotLoanException("El libro seleccionado lamentablemente esta agotado");
            }
            else{
                LoanEntity loanEntity = new LoanEntity();
                loanEntity.setId(loanDto.getId());
                loanEntity.setUserEntity(UserEntity.builder().id(loanDto.getUserId()).build());
                loanEntity.setBookEntity(BookEntity.builder().id(loanDto.getBookId()).build());
                loanEntity.setLoanDate(loanDto.getLoanDate());
                loanEntity.setReturnDate(loanDto.getReturnDate());
                bookService.decreaseQtyBooks(loanDto.getBookId());
                return loanRepository.save(loanEntity).toDto();
            }
        }
    }

    @Transactional
    public LoanResponseDto update(LoanDto loanDto){
        if(findById(loanDto.getId()) == null){
            throw new RuntimeException("El prestamo no existe");
        }else{
            if(userService.findById(loanDto.getUserId()) == null){
                throw new RuntimeException("El usuario no existe");
            }
            if(bookService.findById(loanDto.getBookId())== null){
                throw new RuntimeException("El libro no existe");
            }else{
                LoanEntity loanEntity = new LoanEntity();
                loanEntity.setId(loanDto.getId());
                loanEntity.setUserEntity(UserEntity.builder().id(loanDto.getUserId()).build());
                loanEntity.setBookEntity(BookEntity.builder().id(loanDto.getBookId()).build());
                bookService.increaseQtyBooks(loanDto.getBookId());
                return loanRepository.save(loanEntity).toDto();
            }
        }
    }

    public String fineLoan(Long id){
        LoanResponseDto loanResponseDto = findById(id);
        if(loanResponseDto!= null ){
            LocalDate returnDate = loanResponseDto.getReturnDate();
            Period period = Period.between(LocalDate.now(),returnDate);
            int days = period.getDays();
            return  "Cantidad de dias de atraso " + days;
        }
        return null;
    }

}
