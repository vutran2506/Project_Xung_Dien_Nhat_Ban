import {Component, OnInit} from '@angular/core';
import {ProductService} from "../../service/product.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ProductDTO} from "../../model/product-dto";
import {ShareService} from "../../security-authentication/service/share.service";
import {TokenStorageService} from "../../security-authentication/service/token-storage.service";
import {CartService} from "../../service/cart.service";
import Swal from "sweetalert2";
import {ShareDataService} from "../../service/share-data.service";

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {
  product: ProductDTO;
  id: number;
  email: string;
  amount=1;
  idProduct: number;
 totalProduct: number;

  constructor(private productService: ProductService,
              private activatedRoute: ActivatedRoute,
              private cartService: CartService,
              private shareService: ShareService,
              private tokenStorageService: TokenStorageService,
              private shareDataService:ShareDataService,
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
      this.amount = +value;
      this.idProduct = id;
      this.cartService.addToCart(this.amount, this.idProduct, this.email).subscribe(data => {
        this.shareDataService.getTotalProduct().subscribe(totalProduct => {
          this.totalProduct = totalProduct;
          this.shareService.sendClickEvent();
        });
        Swal.fire({
          icon: 'success',
          iconColor: 'darkorange',
          title: 'Thêm vào giỏ hàng thành công.',

          confirmButtonText: 'Xác nhận',
          confirmButtonColor: 'darkorange'
        })
      })
    } else {
      Swal.fire({
        title: 'Bạn cần đăng nhập tài khoản.',
        confirmButtonColor: 'darkorange'
      })
      this.router.navigateByUrl('/security/login');
    }
  }

  plusAmount() {
this.amount ++;
  }

  minusAmount() {
this.amount--
  }
}
