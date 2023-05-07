import {Component, OnInit} from '@angular/core';
import {ProductService} from "../../service/product.service";
import {ProductDTO} from "../../model/product-dto";
import {ImageService} from "../../service/image.service";
import {Image} from "../../model/image";
import {ProductTypeService} from "../../service/product-type.service";
import {ProductType} from "../../model/product-type";
import {ActivatedRoute, NavigationEnd, Router} from "@angular/router";
import Swal from "sweetalert2";
import {CartService} from "../../service/cart.service";
import {ShareService} from "../../security-authentication/service/share.service";
import {TokenStorageService} from "../../security-authentication/service/token-storage.service";
import {ShareDataService} from "../../service/share-data.service";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  products: ProductDTO[];
  productTypes: ProductType[]
  product: any
  name: string;
  page = 0;
  pageSize = 2;
  pageCount = 0;
  nameSearch = '';
  message: string;
  amount: 1;
  email: string;
  productId: number;
  totalProduct: number;

  constructor(
    private productTypeService: ProductTypeService,
    private productService: ProductService,
    private activatedRoute: ActivatedRoute,
    private cartService: CartService,
    private shareService: ShareService,
    private tokenStorageService: TokenStorageService,
    private shareDataService: ShareDataService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.getAllProduct();
    this.getAllProductType();
    this.view()
  }

  getAllProduct() {
    this.productService.getAllPageProduct(this.nameSearch, this.page, this.pageSize).subscribe(items => {
      this.product = items;
      if (items == null) {
        this.message = "Không tìm thấy kết quả nào với từ khoá : " + this.nameSearch;
      } else {
        this.message = null;
      }
      this.pageCount = this.product.totalPages;
      this.products = this.product.content;
    })
  }

  getAllProductType() {
    this.productTypeService.getAllProductType().subscribe(item => {
      this.productTypes = item;
    })
  }

  next() {
    this.page++;
    this.getAllProduct();
  }


  previous() {
    this.page--;
    this.getAllProduct();
  }

  first() {
    this.page = 0;
    this.getAllProduct();
  }

  last() {
    this.page = this.pageCount - 1;
    this.getAllProduct();
  }

  search(name: string) {
    this.nameSearch = name;
    this.page = 0;
    this.pageSize = 3;
    this.getAllProduct()
  }

  view(): void {
    const element = document.getElementById('homepage');
    if (element) {
      element.scrollIntoView();
    }
  }

  buyNow(id: number) {
    if (this.tokenStorageService.getToken()) {
      this.email = this.tokenStorageService.getUser().username;
      this.amount = +1;
      this.productId = id;
      this.cartService.buyNowCart( this.productId, this.email).subscribe(data => {
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
}
