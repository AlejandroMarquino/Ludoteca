package com.ccsw.tutorial.leasing;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.ccsw.tutorial.leasing.model.Leasing;
import com.ccsw.tutorial.leasing.model.LeasingDto;

//@author AMF - Clase service.

public interface LeasingService {

    Leasing get(Long id);

    // Método para obtener el listado paginado de juegos prestados y a qué cliente
    // están prestados.
    Page<Leasing> findPage(Long customerId, Long gameId, Date searchDate, LeasingDto dto);

    // Método para listar los juegos que están actualmente prestados entre fechas
    // determinadas.
    List<Leasing> findLeasingGame(Long game_id, Date leasingDate, Date endDate);

    // Método para listar los clientes que tienen actualmente juegos prestados entre
    // fechas determinadas.
    List<Leasing> findCustomerWithLeasing(Long customer_id, Date leasingDate, Date endDate);

    // Método para crear o actualizar un préstamo.
    int save(Long id, LeasingDto data);

    // Método para eliminar un préstamo.
    void delete(Long id);
}
