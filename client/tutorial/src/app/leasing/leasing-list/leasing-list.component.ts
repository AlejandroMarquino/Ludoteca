import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { DialogConfirmationComponent } from 'src/app/core/dialog-confirmation/dialog-confirmation.component';
import { Pageable } from 'src/app/core/model/page/Pageable';
import { LeasingEditComponent } from '../leasing-edit/leasing-edit.component';
import { CustomerService } from 'src/app/customer/customer.service';
import { Customer } from 'src/app/customer/model/Customer';
import { LeasingService } from '../leasing.service';
import { Leasing } from '../model/Leasing';
import { GameService } from 'src/app/game/game.service';
import { Game } from 'src/app/game/model/Game';


@Component({
  selector: 'app-leasing-list',
  templateUrl: './leasing-list.component.html',
  styleUrls: ['./leasing-list.component.scss']
})

export class LeasingListComponent implements OnInit {

    pageNumber: number = 0;
    pageSize: number = 5;
    totalElements: number = 0;

    customers : Customer[];
    games : Game[];
    filterCustomer: Customer;
    filterGame: Game;
    customerId: number;
    gameId: number;
    searchDate: Date;
    dataSource = new MatTableDataSource<Leasing>();
    displayedColumns: string[] = ['id','game','customer','leasingDate','endDate','action'];

    constructor(
        private leasingService: LeasingService,
        private gameService : GameService,
        private customeService: CustomerService,
        public dialog: MatDialog,
    ) { }

    ngOnInit(): void {

        this.customeService.getCustomer().subscribe(
            customers => this.customers = customers
        );

        this.gameService.getGames().subscribe(
            games => this.games = games
        );

        this.loadPage();
    }

    /**
     * Permite crear un nuevo préstamo abriendo el cuadro de diálogo vacío.
     */
    createLeasing() {
        
        const dialogRef = this.dialog.open(LeasingEditComponent, {
            data: {}
        });

        dialogRef.afterClosed().subscribe(result => {

            this.ngOnInit();
        });
    }

    /* Permite borrar un préstamo.*/
    deleteLeasing(leasing: Leasing) {
        const dialogRef = this.dialog.open(DialogConfirmationComponent, {
            data: { title: "Eliminar préstamo", description: "Atención: si elimina el préstamo, se perderán sus datos.<br>¿Desea eliminar el préstamo?" }
        });

        dialogRef.afterClosed().subscribe(result => {
            if (result) {
                this.leasingService.deleteLeasing(leasing.id).subscribe(result => {
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

    /* Carga los datos paginados en la página web. */
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

        this.leasingService.getLeasing(this.customerId, this.gameId, this.searchDate, pageable).subscribe(data => {
            this.dataSource.data = data.content;
            this.pageNumber = data.pageable.pageNumber;
            this.pageSize = data.pageable.pageSize;
            this.totalElements = data.totalElements;
        })
    }
}