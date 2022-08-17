import { Component, Inject,  OnInit } from '@angular/core';
import { Leasing } from '../model/Leasing';
import { LeasingService } from '../leasing.service';
import { Game } from 'src/app/game/model/Game';
import { GameService } from 'src/app/game/game.service';
import { Customer } from 'src/app/customer/model/Customer';
import { CustomerService } from 'src/app/customer/customer.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatDialog } from '@angular/material/dialog';

import { DialogMessageComponent } from 'src/app/core/dialog-message/dialog-message.component';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-leasing-edit',
  templateUrl: './leasing-edit.component.html',
  styleUrls: ['./leasing-edit.component.scss']
})

export class LeasingEditComponent implements OnInit {
    
    leasing: Leasing;
    customers: Customer[];
    games: Game[];

    constructor(
        public dialogRef: MatDialogRef<LeasingEditComponent>,
        @Inject(MAT_DIALOG_DATA) public data: any,
        private gameService: GameService,
        private leasingService: LeasingService,
        private customerService: CustomerService,
        public dialog: MatDialog
        
    ) { }

    ngOnInit(): void {

        this.leasing = new Leasing();
        
        this.customerService.getCustomer().subscribe(
            customers => this.customers = customers
        );

        this.gameService.getGames().subscribe(
            games => this.games = games
        );
    }

    /**
     * Validación para guardar un préstamo
     * El periodo de préstamo máximo solo podrá ser de 14 días. 
     * Si el usuario quiere un préstamo para más de 14 días la aplicación no debe permitirlo mostrando una alerta al intentar guardar.
     * El mismo juego no puede estar prestado a dos clientes distintos en un mismo día.
     * Un mismo cliente no puede tener prestados más de 2 juegos en un mismo día.
    */
    onSave() {

        var final_date = Math.abs((this.leasing.leasing_date.getTime() - this.leasing.end_date.getTime())) / (1000 * 3600 * 24);

        if (this.leasing.end_date < this.leasing.leasing_date){

            const dialogRef = this.dialog.open(DialogMessageComponent, {
                data: { title: "Error en la fecha de devolución",
                description: "Fecha de devolución anterior a la fecha inicial.<br>" 
                + "La fecha debe de ser la misma o posterior."}
            });

            this.leasing.end_date = null;
        }
        else if (final_date >= 14 ) {

            const dialogRef = this.dialog.open(DialogMessageComponent, {
                data: { title: "Supera los días máxomos de préstamo",
                description: "El préstamo no puede ser superior a 14 días.<br>" 
                + "Seleccione una fecha de devolución inferior a 14 días."}
            });

            this.leasing.end_date = null;
        }
        else {
            this.leasingService.saveLeasing(this.leasing).subscribe(response => {

                if (response == 100) {
                    const dialogRef = this.dialog.open(DialogMessageComponent, {
                        data: {
                            title: "Juego prestado",
                            description: "El juego no está disponible para prestar en este periodo.<br>" + 
                            "Seleccione otro periodo para el préstamo."
                        }
                    });

                    this.leasing.leasing_date = null;
                    this.leasing.end_date = null;
                }
                else if (response == 200) {
                    const dialogRef = this.dialog.open(DialogMessageComponent, {
                        data: {
                            title: "Cliente con dos o más préstamos",
                            description: "Este cliente excede el númerio máximo de préstamos.<br>" + 
                            "Seleccione otro periodo para el préstamo."
                        }
                    });

                    this.leasing.leasing_date = null;
                    this.leasing.end_date = null;

                }
                else {
                    this.dialogRef.close();
                }
            });
        }
    }

    onClose() {
        this.dialogRef.close();
    }
}