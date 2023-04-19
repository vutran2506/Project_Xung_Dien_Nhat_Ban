import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from "./component/login/login.component";
import {CartComponent} from "./component/cart/cart.component";
import {DetailComponent} from "./component/detail/detail.component";
import {HomePageComponent} from "./component/home-page/home-page.component";


const routes: Routes = [
  {
    path:'login',component:LoginComponent
  },{
  path:'cart',component:CartComponent
  },{
  path:'detail',component:DetailComponent
  },{
  path:'product',component:HomePageComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
