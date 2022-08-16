import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';

import { Customer } from '../model/Customer';
import { CustomersService } from '../customer.service';
import { CustomerEditComponent } from '../customer-edit/customer-edit.component';
import { DialogConfirmationComponent } from 'src/app/core/dialog-confirmation/dialog-confirmation.component';

@Component({
    selector: 'app-customers-list',
    templateUrl: './customers-list.component.html',
    styleUrls: ['./customers-list.component.scss']
})

export class CustomerListComponent implements OnInit {

    dataSource = new MatTableDataSource<Customer>();
    displayedColumns: string[] = ['id', 'name', 'action'];

    constructor(
        private customersService: CustomersService,
        public dialog: MatDialog,
    ) { }

    ngOnInit(): void {
        this.customersService.getCustomers().subscribe (
            customers => this.dataSource.data = customers
        );
    }

    createCustomer() {
        const dialogRef = this.dialog.open(CustomerEditComponent, {
            data: {}
        });

        dialogRef.afterClosed().subscribe(result => {
            this.ngOnInit();
        });
    }

    editCustomer(customer: Customer) {
        const dialogRef = this.dialog.open(CustomerEditComponent, {
            data: { customer: customer }
        });

        dialogRef.afterClosed().subscribe(result => {
            this.ngOnInit();
        });
    }

    deleteCustomer (customer: Customer) {
        const dialogRef = this.dialog.open(DialogConfirmationComponent, {
            data: { title: "Eliminar cliente", description: "Atención: si elimina el cliente, se perderán sus datos.<br>¿Desea eliminar el cliente?" }
        });

        dialogRef.afterClosed().subscribe(result => {
            if (result) {
                this.customersService.deleteCustomer(customer.id).subscribe(result => {
                    this.ngOnInit();
                });
            }
        });
    }
}