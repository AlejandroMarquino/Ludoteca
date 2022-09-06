import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';

import { Customer } from '../model/Customer';
import { CustomerService } from '../customer.service';
import { CustomerEditComponent } from '../customer-edit/customer-edit.component';
import { DialogConfirmationComponent } from 'src/app/core/dialog-confirmation/dialog-confirmation.component';

@Component({
    selector: 'app-customer-list',
    templateUrl: './customer-list.component.html',
    styleUrls: ['./customer-list.component.scss']
})

export class CustomerListComponent implements OnInit {

    dataSource = new MatTableDataSource<Customer>();
    displayedColumns: string[] = ['id', 'name', 'action'];

    constructor(
        private customerService: CustomerService,
        public dialog: MatDialog,
    ) { }

    ngOnInit(): void {
        this.customerService.getCustomer().subscribe (
            customer => this.dataSource.data = customer
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
            data: { title: "Eliminar cliente", description: "Atención: se perderán los datos.<br>¿Desea eliminar el cliente?" }
        });

        dialogRef.afterClosed().subscribe(result => {
            if (result) {
                this.customerService.deleteCustomer(customer.id).subscribe(result => {
                    this.ngOnInit();
                });
            }
        });
    }}