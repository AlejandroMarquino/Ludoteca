package com.ccsw.tutorial.leasing;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.ccsw.tutorial.leasing.model.Leasing;
import com.ccsw.tutorial.leasing.model.LeasingDto;
import com.ccsw.tutorial.leasing.model.LeasingSearchDto;

public interface LeasingService {

    // método para recuperar listado paginado

    Page<Leasing> findPage(Long customerId, Long gameId, Date searchDate, LeasingSearchDto dto);

    // comprobación de fechas de préstamo de un juego concreto

    List<Leasing> findBorrowedGame(Long game_id, Date leasing_date, Date end_date);

    // prestamos existentes de un cliente concreto entre dos fechas

    List<Leasing> findExistingLeasing(Long customer_id, Date leasing_date, Date end_date);

    // crea o actualiza un préstamo

    int save(Long id, LeasingDto data);

    // borrar un préstamo

    void delete(Long id);

}
