import { Component, OnInit } from '@angular/core';
import {CartService} from "../../service/cart.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Cart} from "../../model/cart";
import {TokenStorageService} from "../../security-authentication/service/token-storage.service";
import {ShareService} from "../../security-authentication/service/share.service";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
   email: string;
   carts: Cart[];

  constructor(private cartService:CartService,
             private shareService:ShareService,
              private tokenStorageService:TokenStorageService,
              private router:Router) { }

  ngOnInit(): void {
this.getUserId()
    this.view()
  }
  getUserId() {
    if (this.tokenStorageService.getToken()) {
      this.email = this.tokenStorageService.getUser().username;
      this.cartService.getCart(this.email).subscribe(data => {
        console.log(data)
        this.carts = data;
      });
    } else {
      this.router.navigateByUrl('/security/login');
    }
  }

  stepDown(cart: Cart) {

  }

  stepUp(cart: Cart) {
cart.amount++;

  }

  view(): void {
    const element = document.getElementById('cart');
    if (element) {
      element.scrollIntoView();
    }
  }

}
