import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { DefaultComponent } from './default.component';
import { ProductComponent } from 'src/app/modules/product/product.component';
import { HomeComponent } from 'src/app/modules/home/home.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { SharedModule } from 'src/app/shared/shared.module';
import { ProductDetailsComponent } from 'src/app/modules/product-details/product-details.component';
import { ProfileComponent } from 'src/app/modules/profile/profile.component';
import { ReactiveFormsModule } from '@angular/forms';
import { CategoryComponent } from 'src/app/modules/category/category.component';
import { CartComponent } from 'src/app/modules/cart/cart.component';
import { OrderComponent } from 'src/app/modules/order/order.component';
import { ReplacePipe } from 'src/app/modules/common/pipe/preplacepipe';



@NgModule({
  declarations: [    
    DefaultComponent,
    HomeComponent,
    ProductComponent,
    ProductDetailsComponent,
    ProfileComponent,
    CategoryComponent,
    CartComponent,
    OrderComponent,
    ReplacePipe

  ],
  imports: [
    CommonModule,
    RouterModule,
    FlexLayoutModule,
    SharedModule,
    ReactiveFormsModule
  ]
})
export class DefaultModule { }
