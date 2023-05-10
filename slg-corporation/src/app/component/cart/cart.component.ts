import {Component, OnInit} from '@angular/core';
import {CartService} from "../../service/cart.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Cart} from "../../model/cart";
import {TokenStorageService} from "../../security-authentication/service/token-storage.service";
import {ShareService} from "../../security-authentication/service/share.service";
import Swal from "sweetalert2";
import {ShareDataService} from "../../service/share-data.service";
import {render} from "creditcardpayments/creditCardPayments";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  email: string;
  listError: string[];
  carts: Cart[];
  total = 0;
  totalAmount = 0;
  quantityUpdate = 0;
  id: number;
  totalProduct: number;
  deleteCart: Cart;
  idCartDetail: number;
  checkQuantityCart = 0;
  message: string;
  idProduct: number;
  convertCurrency: any;
  isRender = false;

  constructor(private cartService: CartService,
              private shareService: ShareService,
              private tokenStorageService: TokenStorageService,
              private shareDataService: ShareDataService,
              private router: Router) {

  }

  ngOnInit(): void {
    this.getUserId()
  }

  getUserId() {
    if (this.tokenStorageService.getToken()) {
      this.email = this.tokenStorageService.getUser().username;
      this.cartService.getCart(this.email).subscribe(data => {
        this.carts = data;
        this.total = 0;
        this.totalAmount = 0;
        this.checkQuantityCart = this.carts.length;
        for (let i = 0; i < this.carts.length; i++) {
          this.carts[i].totalItem = this.carts[i].price * this.carts[i].amount;
          this.total += this.carts[i].price * this.carts[i].amount;
          this.totalAmount += this.carts[i].amount;
          this.view()
        }
        this.convertCurrency = this.total / 23000;
        this.shareDataService.getTotalProduct().subscribe(totalProduct => {
          this.totalProduct = totalProduct;
          this.shareService.sendClickEvent();
        });
        this.convertCurrency = Math.round(this.convertCurrency * 100) / 100;
        this.isRender = false;
      });
    } else {
      this.router.navigateByUrl('/security/login');
    }
  }

  stepDown(cart: Cart) {
    if (cart.amount <= 1) {
      Swal.fire({
        title: 'Số lượng sản phẩm không được bằng không',
      })
    } else {
      cart.amount--
      this.quantityUpdate = cart.amount
      this.idCartDetail = cart.id;
      this.email = this.tokenStorageService.getUser().username;
      this.cartService.updateToCart(this.quantityUpdate, this.idCartDetail, this.email).subscribe(next => {
        this.getUserId()
      });

      this.view()
    }

  }

  stepUp(cart: Cart) {
    cart.amount++;
    this.quantityUpdate = cart.amount
    this.idCartDetail = cart.id;
    this.email = this.tokenStorageService.getUser().username;
    this.cartService.updateToCart(this.quantityUpdate, this.idCartDetail, this.email).subscribe(next => {
      this.getUserId()
    });

    this.view()
  }

  view(): void {
    const element = document.getElementById('cart');
    if (element) {
      element.scrollIntoView();
    }
  }

  updateQuantity(amount: string, id: number) {

    this.quantityUpdate = +amount;
    this.idCartDetail = id;
    this.email = this.tokenStorageService.getUser().username;
    this.cartService.updateToCart(this.quantityUpdate, this.idCartDetail, this.email).subscribe(next => {
      this.getUserId()
    });

    this.view()
  }

  deleteAllCart(cart: Cart) {
    this.deleteCart = cart;
  }

  delete(idProduct: number) {
    this.idProduct = idProduct;
    debugger
    this.email = this.tokenStorageService.getUser().username;
    this.cartService.removeCart(this.idProduct, this.email).subscribe(next => {

      Swal.fire({
        icon: 'success',
        iconColor: 'darkorange',
        title: 'Xoá sản phẩm ra khỏi giỏ hàng thành công.',

        confirmButtonText: 'Xác nhận',
        confirmButtonColor: 'darkorange'

      });
      this.getUserId()
    });

  }


  payCart(convertCurrency: number) {
    this.listError = undefined;
    this.cartService.payCart().subscribe(next => {
      this.convertCurrency = convertCurrency
      this.isRender = true;
      document.querySelector('#myPaypalButtons').innerHTML = '';
      render(
        {
          id: "#myPaypalButtons",
          currency: "USD",
          value: this.convertCurrency,
          onApprove: (details => {
            this.getUserId()
            Swal.fire({
              icon: 'success',
              iconColor: 'darkorange',
              title: 'Đã thanh toán khỏi giỏ hàng thành công.',
              confirmButtonText: 'Xác nhận',
              confirmButtonColor: 'darkorange'

            });
          })
        })
    }, error => {
      this.getUserId()
      this.listError = error.error;
      console.log(this.listError)
      Swal.fire({
        icon: 'success',
        iconColor: 'darkorange',
        title: 'Thanh toán thất bại.Vui lòng kiểm tra lại.',
        confirmButtonText: 'Xác nhận',
        confirmButtonColor: 'darkorange'

      });
    });
  }

}
