import { Component, OnInit } from '@angular/core';
import {ProductService} from "../../service/product.service";
import {ProductDTO} from "../../model/product-dto";
import {ImageService} from "../../service/image.service";
import {Image} from "../../model/image";
import {ProductTypeService} from "../../service/product-type.service";
import {ProductType} from "../../model/product-type";
import {NavigationEnd, Router} from "@angular/router";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  products:ProductDTO[];
  productTypes:ProductType[]
  product: any
  name:string;
  page = 0;
  pageSize = 2;
  pageCount = 0;
  nameSearch= '';
  message:string;

  constructor(
    private productService:ProductService,
    private productTypeService:ProductTypeService,
    private router:Router
  ) { }

  ngOnInit(): void {
    this.getAllProduct();
    this.getAllProductType();
    this.view()
  }
  getAllProduct(){
    this.productService.getAllPageProduct(this.nameSearch,this.page,this.pageSize).subscribe(items =>{
      this.product = items;
      if (items == null){
        this.message="Không tìm thấy sản phẩm";
      }else {
        this.message = null;
      }
      this.pageCount= this.product.totalPages;
      this.products = this.product.content;
    })
  }
  getAllProductType(){
    this.productTypeService.getAllProductType().subscribe(item =>{
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

  search(name:string) {
    this.nameSearch = name;
     this.getAllProduct()
  }

  view(): void {
    const element = document.getElementById('homepage');
    if (element) {
      element.scrollIntoView();
    }
  }
}
