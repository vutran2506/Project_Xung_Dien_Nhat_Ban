import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import {CartComponent} from "./component/cart/cart.component";
import {DetailComponent} from "./component/detail/detail.component";
import {HomePageComponent} from "./component/home-page/home-page.component";
import {UserGuard} from "./security-authentication/security-auth/user.guard";
import {AdminGuard} from "./security-authentication/security-auth/admin.guard";



const routes: Routes = [
  {path: 'security',
    loadChildren: () => import('./security-authentication/security-authentication.module')
      .then(module => module.SecurityAuthenticationModule)
  },{
  path:'cart',component:CartComponent, canActivate: [UserGuard]
  },{
  path:'detail/:id',component:DetailComponent, canActivate: [UserGuard]
  },
  {
  path:'product',component:HomePageComponent, canActivate: [UserGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{scrollPositionRestoration: 'enabled'})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
