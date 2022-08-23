import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CoreModule } from './core/core.module';
import { CategoryModule } from './category/category.module';
import { AuthorModule } from './author/author.module';
import { GameModule } from './game/game.module';
import { CustomersModule } from './customer/customer.module';
import { LeasingModule } from './leasing/leasing.module';



@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CoreModule,
    BrowserAnimationsModule,
    CategoryModule,
    AuthorModule,
    GameModule,
    CustomersModule,
    LeasingModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
