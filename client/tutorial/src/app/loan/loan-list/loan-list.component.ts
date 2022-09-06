import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { DialogConfirmationComponent } from 'src/app/core/dialog-confirmation/dialog-confirmation.component';
import { Pageable } from 'src/app/core/model/page/Pageable';
import { LoanEditComponent } from '../loan-edit/loan-edit.component';
import { CustomerService } from 'src/app/customer/customer.service';
import { Customer } from 'src/app/customer/model/Customer';
import { LoanService } from '../loan.service';
import { Loan } from '../model/Loan';
import { GameService } from 'src/app/game/game.service';
import { Game } from 'src/app/game/model/Game';


@Component({
  selector: 'app-loan-list',
  templateUrl: './loan-list.component.html',
  styleUrls: ['./loan-list.component.scss']
})

export class LoanListComponent implements OnInit {

    pageNumber: number = 0;
    pageSize: number = 5;
    totalElements: number = 0;

    customer : Customer[];
    games : Game[];
    filterCustomer: Customer;
    filterGame: Game;
    customerId: number;
    gameId: number;
    searchDate: Date;
    dataSource = new MatTableDataSource<Loan>();
    displayedColumns: string[] = ['id','game','customer','loan_date','end_date','action'];

    constructor(
        private loanService: LoanService,
        private customerService: CustomerService,
        private gameService : GameService,
        public dialog: MatDialog,
    ) { }

    ngOnInit(): void {

        this.customerService.getCustomer().subscribe(
            customer => this.customer = customer
        );

        this.gameService.getGames().subscribe(
            games => this.games = games
        );

        this.loadPage();
    }

    /**
     * Permite crear un nuevo préstamo abriendo el cuadro
     * de diálogo vacío.
     */
    createLoan() {
        
        const dialogRef = this.dialog.open(LoanEditComponent, {
            data: {}
        });

        dialogRef.afterClosed().subscribe(result => {

            this.ngOnInit();
        });
    }

    /**
     * Permite borrar un préstamo.
     * 
     * @param loan Préstamo a borrar.
     */
    deleteLoan(loan: Loan) {
        const dialogRef = this.dialog.open(DialogConfirmationComponent, {
            data: { title: "Eliminar préstamo", description: "Atención: si elimina el préstamo, se perderán sus datos.<br>¿Desea eliminar el préstamo?" }
        });

        dialogRef.afterClosed().subscribe(result => {
            if (result) {
                this.loanService.deleteLoan(loan.id).subscribe(result => {
                    this.ngOnInit();
                });
            }
        });

    }
    /**
     * Desencadena una búsqueda por cliente, juego o fecha
     * especificados.
     */
    onSearch(): void {

        this.customerId = this.filterCustomer != null ? this.filterCustomer.id : null;
        this.gameId = this.filterGame != null ? this.filterGame.id : null;

        this.loadPage();
    }

    /**
     * Borra los filtros de la búsqueda y devuelve
     * todos los préstamos de la Base de Datos.
     */
    onCleanFilter(): void {

        this.customerId = null;
        this.gameId = null;
        this.searchDate = null;

        this.filterCustomer = null;
        this.filterGame = null;

        this.loadPage();
    }

    /**
     * Carga los datos paginados en la página web.
     * 
     * @param event Evento desencadenante de la carga.
     */
    loadPage(event?: PageEvent) {

        let pageable: Pageable = {
            pageNumber: this.pageNumber,
            pageSize: this.pageSize,
            sort: [{
                property: 'id',
                direction: 'ASC'
            }]
        }

        if (event != null) {
            pageable.pageSize = event.pageSize;
            pageable.pageNumber = event.pageIndex;
        }

        this.loanService.getLoans(this.customerId, this.gameId, this.searchDate, pageable).subscribe(data => {
            this.dataSource.data = data.content;
            this.pageNumber = data.pageable.pageNumber;
            this.pageSize = data.pageable.pageSize;
            this.totalElements = data.totalElements;
        })
    }
}