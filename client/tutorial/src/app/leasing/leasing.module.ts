import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LeasingListComponent } from './leasing-list/leasing-list.component';
import { LeasingEditComponent } from './leasing-edit/leasing-edit.component';



@NgModule({
  declarations: [
    LeasingListComponent,
    LeasingEditComponent
  ],
  imports: [
    CommonModule
  ]
})
export class LeasingModule { }
