import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DefaultModule } from './layouts/default/default.module';
import { FullpageModule } from './layouts/fullpage/fullpage.module';
import { FullpageadminModule } from './layouts/fullpageadmin/fullpageadmin.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { FullpageadminemptyModule } from './layouts/fullpageadminempty/fullpageadminempty.module';
import { JwtInterceptor } from './modules/common/interceptor/jwt.interceptor';
import { AdminAuthorizedGuard } from './modules/common/guard/adminAuthorizedGuard';
import { ProfileAuthorizedGuard } from './modules/common/guard/profileAuthorizedGuard';
import { CookieService } from 'ngx-cookie-service';
@NgModule({
  declarations: [
    AppComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    DefaultModule,
    FullpageModule,
    FullpageadminModule,
    FullpageadminemptyModule,
    BrowserAnimationsModule,
    HttpClientModule
  ],

  providers: [
    CookieService,
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    AdminAuthorizedGuard,
    ProfileAuthorizedGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
