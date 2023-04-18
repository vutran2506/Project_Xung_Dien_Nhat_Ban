import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HeaderComponent} from "./component/layout/header/header.component";
import {FooterComponent} from "./component/layout/footer/footer.component";
import {DetailComponent} from "./component/detail/detail.component";
import {HomePageComponent} from "./component/home-page/home-page.component";
import { CartComponent } from './component/cart/cart.component';
import { LoginComponent } from './component/login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    DetailComponent,
    HomePageComponent,
    CartComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
