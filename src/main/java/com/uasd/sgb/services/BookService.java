package com.uasd.sgb.services;

import com.uasd.sgb.dto.request.BookDto;
import com.uasd.sgb.dto.response.BookCollectionResponse;
import com.uasd.sgb.dto.response.BookResponseDto;
import com.uasd.sgb.entity.BookEntity;
import com.uasd.sgb.repository.BookRepository;
import com.uasd.sgb.utils.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;


    public BookResponseDto findById(Long id){
        return  bookRepository.findById(id).orElseThrow().toDto();
    }

    public BookCollectionResponse findAll(Integer page, Integer pageSize){
        Page<BookEntity> entityPage = bookRepository.findAll(PageRequest.of(page,pageSize, Sort.by("id").descending()));
        return BookCollectionResponse.builder()
                .page(page)
                .pageSize(pageSize)
                .totalPages(entityPage.getTotalPages()-1)
                .totalElements(entityPage.getTotalElements())
                .bookResponseDtoList(entityPage.stream().map(BookEntity::toDto).toList())
                .build();
    }

    @Transactional
    public BookResponseDto save(BookDto bookDto){
        BookEntity bookEntity = BookEntity.builder().build().from(bookDto);
        return bookRepository.save(bookEntity).toDto();
    }

    @Transactional
    public BookResponseDto update(BookDto bookDto){
        BookResponseDto bookResponseDto = findById(bookDto.getId());
        if(bookResponseDto == null){
            throw  new NotFoundException("El libro no exitste");
        }else {
            BookEntity bookEntity = new BookEntity();
            bookEntity.setId(bookDto.getId());
            bookEntity.setAuthor(bookDto.getAuthor());
            bookEntity.setTitle(bookDto.getTitle());
            bookEntity.setIsbn(bookDto.getIsbn());
            bookEntity.setAvailableQty(bookDto.getAvailableQty());
            return bookRepository.save(bookEntity).toDto();
        }
    }

    @Transactional
    public String delete(Long id){
        if(findById(id) == null){
            throw  new NotFoundException("El libro no exitste");
        }else {
            bookRepository.deleteById(id);
            return "Libro eliminado existosamente";
        }
    }

    public void decreaseQtyBooks(Long id){
        bookRepository.rebajarInventario(id);
    }

    public void increaseQtyBooks(Long id){
        bookRepository.aumentarInventario(id);
    }
}
