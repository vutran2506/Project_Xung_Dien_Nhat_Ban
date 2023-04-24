import { Component, OnInit } from '@angular/core';
import {ProductService} from "../../service/product.service";
import {ProductDTO} from "../../model/product-dto";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  products:ProductDTO[];
  product: any
  name:string;
  origin:number;
  page = 0;
  pageSize = 3;
  pageCount = 0;
  pageNumbers: number[] = [];

  constructor(
    private productService:ProductService
  ) { }

  ngOnInit(): void {
    this.getAllProduct();
  }
  getAllProduct(){
    this.productService.getAllPageProduct(this.name,this.origin,this.page,this.pageSize).subscribe(items =>{
      console.log(items)
      this.product = items;
      this.products = this.product.content
    })
  }
}
