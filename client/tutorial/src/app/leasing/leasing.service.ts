import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Pageable } from '../core/model/page/Pageable';
import { Leasing } from './model/Leasing';
import { LeasingPage } from './model/LeasingPage';

@Injectable({
    providedIn: 'root'
})

export class LeasingService {

    constructor(
        private http: HttpClient
    ) { }

    // muestra los préstamos de manera paginada 
    getLeasings(customerId? :number, gameId? : number, searchDate?: Date,  pageable? : Pageable) : Observable<LeasingPage> {

        return this.http.post<LeasingPage>(this.composeFindUrl(customerId, gameId, searchDate), {pageable:pageable});
    }

    // Guarda préstamo
    saveLeasing(leasing: Leasing): Observable<any> {
        
        let url = 'http://localhost:8080/leasing';
        if (leasing.id != null) url += '/'+leasing.id;

        return this.http.put(url, leasing, {observe: 'body'});
    }

    // Borra un préstamo 
    deleteLeasing(idLeasing: number): Observable<any> {
        
        return this.http.delete('http://localhost:8080/leasing/'+idLeasing);
    }

    // Muestra los préstamos según cliente, juego o fecha.  
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

        let url = 'http://localhost:8080/leasing';

        if (params == '') return url;
            else return url + '?' + params;

    }
}