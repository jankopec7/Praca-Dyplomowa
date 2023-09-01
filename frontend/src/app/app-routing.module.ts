import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DefaultComponent } from './layouts/default/default.component';
import { FullpageComponent } from './layouts/fullpage/fullpage.component';
import { FullpageadminComponent } from './layouts/fullpageadmin/fullpageadmin.component';
import { AdminProductAddComponent } from './modules/admin/admin-product-add/admin-product-add.component';
import { AdminProductUpdateComponent } from './modules/admin/admin-product-update/admin-product-update.component';
import { AdminProductComponent } from './modules/admin/admin-product/admin-product.component';
import { AdminComponent } from './modules/admin/admin.component';
import { HomeComponent } from './modules/home/home.component';
import { LoginComponent } from './modules/login/login.component';
import { ProductDetailsComponent } from './modules/product-details/product-details.component';
import { ProductComponent } from './modules/product/product.component';
import { AdminLoginComponent } from './modules/admin/admin-login/admin-login.component';
import { FullpageadminemptyComponent } from './layouts/fullpageadminempty/fullpageadminempty.component';
import { AdminAuthorizedGuard } from './modules/common/guard/adminAuthorizedGuard';
import { ProfileComponent } from './modules/profile/profile.component';
import { ProfileAuthorizedGuard } from './modules/common/guard/profileAuthorizedGuard';

const routes: Routes = [
  {
    path:'', component: DefaultComponent, children: [
      {path: '', component: HomeComponent},
      {path: 'products', component: ProductComponent},
      {path: 'products/:slug', component: ProductDetailsComponent},
    ]
  },
  {
    path:'', component: FullpageComponent, children: [
      {path: 'login', component: LoginComponent}
    ]
  },
  {
    path:'', component: FullpageadminComponent, children: [
      {path: 'admin', component: AdminComponent, canActivate: [AdminAuthorizedGuard]},
      {path: 'admin/products', component: AdminProductComponent, canActivate: [AdminAuthorizedGuard]},
      {path: 'admin/products/update/:id', component: AdminProductUpdateComponent, canActivate: [AdminAuthorizedGuard]},
      {path: 'admin/products/add', component: AdminProductAddComponent, canActivate: [AdminAuthorizedGuard]}
    ]
  },
  {
    path:'', component: FullpageadminemptyComponent, children: [
      {path: 'admin/login', component: AdminLoginComponent}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {onSameUrlNavigation: 'reload'})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
