package com.uasd.sgb.repository;

import com.uasd.sgb.entity.BookEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository  extends JpaRepository<BookEntity,Long> {


    @Modifying
    @Query(value = "UPDATE public.tb_book " +
            " SET cantidad_disponible = cantidad_disponible - 1 " +
            " WHERE id_libro = :idBook", nativeQuery = true)
    void rebajarInventario(Long idBook);

    @Modifying
    @Query(value = "UPDATE public.tb_book " +
            " SET cantidad_disponible = cantidad_disponible + 1 " +
            " WHERE id_libro = :idBook", nativeQuery = true)
    void aumentarInventario(Long idBook);
}
