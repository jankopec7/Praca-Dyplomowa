import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { DefaultComponent } from './default.component';
import { ProductComponent } from 'src/app/modules/product/product.component';
import { HomeComponent } from 'src/app/modules/home/home.component';
import { FlexLayoutModule } from '@angular/flex-layout';



@NgModule({
  declarations: [    
    DefaultComponent,
    ProductComponent,
    HomeComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FlexLayoutModule
  ]
})
export class DefaultModule { }
