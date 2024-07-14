package com.uasd.sgb.entity;

import com.uasd.sgb.dto.request.LoanDto;
import com.uasd.sgb.dto.response.LoanResponseDto;
import com.uasd.sgb.utils.ToDTO;
import com.uasd.sgb.utils.TransformFrom;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "tb_loan")
public class LoanEntity implements TransformFrom<LoanDto, LoanEntity>, ToDTO<LoanResponseDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prestamo",updatable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_usuario",updatable = false)
    private UserEntity userEntity;

    @OneToOne
    @JoinColumn(name = "id_libro",updatable = false)
    private BookEntity bookEntity;

    @Column(name = "fecha_prestamo",updatable = false)
    private LocalDate loanDate;

    @Column(name = "fecha_devolucion")
    private LocalDate returnDate;


    @Override
    public LoanResponseDto toDto() {
        return LoanResponseDto.builder()
                .id(this.id)
                .bookResponseDto(this.bookEntity.toDto())
                .userResponseDto(this.userEntity.toDto())
                .loanDate(this.loanDate)
                .returnDate(this.returnDate)
                .build();
    }

    @Override
    public LoanEntity from(LoanDto loanDto) {
        return LoanEntity.builder()
                .bookEntity(BookEntity.builder().id(loanDto.getBookId()).build())
                .userEntity(UserEntity.builder().id(loanDto.getUserId()).build())
                .loanDate(loanDto.getLoanDate())
                .returnDate(loanDto.getReturnDate())
                .build();
    }
}
