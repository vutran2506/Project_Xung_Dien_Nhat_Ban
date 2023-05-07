import { Injectable } from '@angular/core';
import {TokenStorageService} from "../security-authentication/service/token-storage.service";
import {CartService} from "./cart.service";
import {Router} from "@angular/router";
import {Cart} from "../model/cart";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ShareDataService {
  totalProduct = 0;
  email: string;
  cartList: Cart[];
  totalPrice = 0;

  constructor(private tokenStorageService: TokenStorageService,
              private cartService: CartService,
              private router: Router) { }
  getTotalProduct(): Observable<number> {
    return new Observable<number>((observer) => {
      if (this.tokenStorageService.getToken()) {
        this.email = this.tokenStorageService.getUser().username;
        this.cartService.getCart(this.email).subscribe(data => {
          this.cartList = data;
          this.totalProduct =0;
          for (let i = 0; i < this.cartList.length; i++) {
            this.totalProduct += this.cartList[i].amount;
          }
          observer.next(this.totalProduct);
          observer.complete();
        });
      } else {
        this.router.navigateByUrl('/security/login');
        observer.next(0);
        observer.complete();
      }
    });
  }
}
