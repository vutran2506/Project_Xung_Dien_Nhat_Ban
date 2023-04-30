import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import {CartComponent} from "./component/cart/cart.component";
import {DetailComponent} from "./component/detail/detail.component";
import {HomePageComponent} from "./component/home-page/home-page.component";




const routes: Routes = [
  {path: 'security',
    loadChildren: () => import('./security-authentication/security-authentication.module')
      .then(module => module.SecurityAuthenticationModule)
  },{
  path:'cart',component:CartComponent
  },{
  path:'detail/:id',component:DetailComponent
  },
  {
  path:'',component:HomePageComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{scrollPositionRestoration: 'enabled'})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
