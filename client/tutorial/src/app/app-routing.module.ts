import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthorListComponent } from './author/author-list/author-list.component';
import { CategoryListComponent } from './category/category-list/category-list.component';
import { GameListComponent } from './game/game-list/game-list.component';
import { CustomerListComponent } from './customer/customer-list/customer-list.component';
import { LoanListComponent } from './loan/loan-list/loan-list.component';

const routes: Routes = [
  { path: 'categories', component: CategoryListComponent},
  { path: 'authors' , component: AuthorListComponent},
  { path: 'games', component: GameListComponent},
  { path: '', redirectTo: '/games', pathMatch: 'full'},
  { path: 'customer', component: CustomerListComponent},
  { path: 'loan', component: LoanListComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
