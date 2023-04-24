import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HeaderComponent} from "./component/layout/header/header.component";
import {FooterComponent} from "./component/layout/footer/footer.component";
import {DetailComponent} from "./component/detail/detail.component";
import {HomePageComponent} from "./component/home-page/home-page.component";
import {CartComponent} from './component/cart/cart.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {AuthInterceptor} from "./security-authentication/security-auth/auth.interceptor";


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    DetailComponent,
    HomePageComponent,
    CartComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule
  ],
  // providers: [],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
