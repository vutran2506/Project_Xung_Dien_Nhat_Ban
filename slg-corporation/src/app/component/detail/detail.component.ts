import {Component, OnInit} from '@angular/core';
import {ProductService} from "../../service/product.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ProductDTO} from "../../model/product-dto";
import {ShareService} from "../../security-authentication/service/share.service";
import {TokenStorageService} from "../../security-authentication/service/token-storage.service";
import {CartService} from "../../service/cart.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {
  product: ProductDTO;
  id: number;
  email: string;
  amount: number;
  idProduct: number;

  constructor(private productService: ProductService,
              private activatedRoute: ActivatedRoute,
              private cartService: CartService,
              private shareService: ShareService,
              private tokenStorageService: TokenStorageService,
              private router: Router) {

  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(param => {
      this.id = +param.get('id');
    });
    this.productService.findProductBbyId(this.id).subscribe(item => {
      console.log(item)
      this.product = item;
    })
    this.view()
  }

  view(): void {
    const element = document.getElementById('detail');
    if (element) {
      element.scrollIntoView();
    }
  }

  addTCart(value: string, id: number) {
    if (this.tokenStorageService.getToken()) {
      this.email = this.tokenStorageService.getUser().username;
      console.log(this.email)
      this.amount = +value;
      console.log(this.amount)
      this.idProduct = id;
      console.log(this.idProduct)
      this.cartService.addToCart(this.amount, this.idProduct, this.email).subscribe(data => {
        debugger
        Swal.fire({
          icon: 'success',
          iconColor: 'darkorange',
          title: 'Thêm vào giỏ hàng thành công.',

          confirmButtonText: 'Xác nhận',
          confirmButtonColor: 'darkorange'
        })
      })
    } else {
      this.router.navigateByUrl('/security/login');
    }
  }
}
