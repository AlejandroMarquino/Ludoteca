import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Pageable } from '../core/model/page/Pageable';
import { Loan } from './model/Loan';
import { LoanPage } from './model/LoanPage';

@Injectable({
    providedIn: 'root'
})

export class LoanService {

    constructor(
        private http: HttpClient
    ) { }

    /**
     * Obtiene los préstamos de juegos realizados.
     * 
     * 
     * @param pageable Objeto Angular Pageable. Permite
     *                 paginar los datos.
     * @returns Listado por páginas de los préstamos.
     */
    getLoans(customerId? :number, gameId? : number, searchDate?: Date,  pageable? : Pageable) : Observable<LoanPage> {

        return this.http.post<LoanPage>(this.composeFindUrl(customerId, gameId, searchDate), {pageable:pageable});
    }

    /**
     * Guarda un préstamo en la base de datos.
     * @param loan Préstamo a guardar.
     */
    saveLoan(loan: Loan): Observable<any> {
        
        let url = 'http://localhost:8080/loan';
        if (loan.id != null) url += '/'+loan.id;

        return this.http.put(url, loan, {observe: 'body'});
    }

    /**
     * Borra un préstamo de la base de datos.
     * @param idLoan Id del préstamo a borrar.
     */
    deleteLoan(idLoan: number): Observable<any> {
        
        return this.http.delete('http://localhost:8080/loan/'+idLoan);
    }

    /**
     * Envía la URL compuesta al backend para que devuelva los
     * préstamos en base al cliente, juego o fecha especificados.
     * 
     * @param customerId Id del cliente.
     * @param gameId Id del juego.
     * @returns URl completa a enviar.
     */
    private composeFindUrl(customerId?: number, gameId?: number, searchDateBeforeConv?: Date) : string {

        let params = '';
        let searchDate = '';

        if (customerId != null){
            params += 'customerId='+customerId;
        }

        if (gameId != null) {
            if (params != '') params += "&";
            params += "gameId=" +gameId;
        }

        if (searchDateBeforeConv != null) {
            if (params != '') params += "&";
            
            searchDate = searchDateBeforeConv.getFullYear().toString() + "-" 
            + (searchDateBeforeConv.getMonth()+1).toString() + "-" 
            + searchDateBeforeConv.getDate().toString() ;
            
            params += "searchDate=" + searchDate;
        }

        let url = 'http://localhost:8080/loan';

        if (params == '') return url;
            else return url + '?' + params;

    }
}