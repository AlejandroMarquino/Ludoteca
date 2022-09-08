package com.ccsw.tutorial.loan;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.ccsw.tutorial.loan.model.Loan;
import com.ccsw.tutorial.loan.model.LoanDto;
import com.ccsw.tutorial.loan.model.LoanSearchDto;

public interface LoanService {

    /**
     * Método para recuperar un listado paginado de
     * {@link com.ccsw.tutorial.loan.model.Loan}
     * 
     * @param dto
     * @return listado paginado
     */
    Page<Loan> findPage(Long customerId, Long gameId, Date searchDate, LoanSearchDto dto);

    /**
     * fechas de un juego si ya está prestado dentro de unas fechas por un cliente
     * 
     * @param game_id
     * @param loan_date
     * @param end_date
     * @return Listado
     */
    List<Loan> findBorrowedGame(Long game_id, Date loan_date, Date end_date);

    /**
     * muestra prestamos existentes
     * 
     * @param customer_id
     * @param loan_date
     * @param end_date
     * @return Listado
     */
    List<Loan> findExistingLoans(Long customer_id, Date loan_date, Date end_date);

    /**
     * Método para crear o actualizar un {@link com.ccsw.tutorial.loan.model.Loan}
     * 
     * @param id
     * @param data
     */
    int save(Long id, LoanDto data);

    /**
     * Método para borrar un {@link com.ccsw.tutorial.loan.model.Loan}
     * 
     * @param id Id
     */
    void delete(Long id);

}