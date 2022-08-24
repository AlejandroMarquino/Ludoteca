package com.ccsw.tutorial.leasing;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ccsw.tutorial.leasing.model.Leasing;

public interface LeasingRepository extends CrudRepository<Leasing, Long> {
    /**
     * Método para recuperar un listado paginado de
     * {@link com.ccsw.tutorial.leasing.model.Leasing}
     * 
     * @param pageable
     * @return
     */

    @Query("select l from Leasing l where (:customer is null or l.customer.id = :customer) and (:game is null or l.game.id = :game) and (:date is null or :date between l.leasing_date and l.end_date)")
    Page<Leasing> find(@Param("customer") Long customer, @Param("game") Long game, @Param("date") Date date,
            Pageable pageable);

    /**
     * Método para recuperar un listado filtado por juego y por fechas de inicio y
     * fin.
     * 
     * @param game        Juego a buscar
     * @param leasingDate Fecha de inicio del préstamo
     * @param endDate     Fecha de devolución del préstamo
     * @return Muestra hay sido prestamos de un juego en el rango de fechas dado por
     *         leasingDate y endDate.
     */

    @Query("select l from Leasing l where (l.game.id = :game) and ((l.leasing_date between :leasing_date and :end_date) or (l.end_date between :leasing_date and :end_date) or (:leasing_date between l.leasing_date and l.end_date) or (:end_date between l.leasing_date and l.end_date))")
    List<Leasing> findBorrowedGame(@Param("game") Long game, @Param("leasing_date") Date leasing_date,
            @Param("end_date") Date end_date);

    /**
     * Método para recuperar un listado filtrado por cliente, fecha de inicio y fin.
     * Permite determinar si un cliente tiene dos o más juegos prestados en el rango
     * 
     * @param customer    Cliente a consultar
     * @param leasingDate Fecha de inicio de préstamo
     * @param endDate     Fecha de devolución del préstamo
     * @return Devuelve si un cliente tiene un préstamo pendiente entre las fechas
     *         indicadas como parámetros leasingDate y endDate.
     * 
     */

    @Query("select l from Leasing l where (l.customer.id = :customer) and ((l.leasing_date between :leasing_date and :end_date) or (l.end_date between :leasing_date and :end_date) or (:leasing_date between l.leasing_date and l.end_date) or (:end_date between l.leasing_date and l.end_date))")
    List<Leasing> findNumberOfLeasing(@Param("customer") Long customer, @Param("leasing_date") Date leasing_date,
            @Param("end_date") Date end_date);

}
