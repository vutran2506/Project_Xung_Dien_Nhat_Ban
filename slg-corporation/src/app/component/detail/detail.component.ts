import {Component, OnInit} from '@angular/core';
import {ProductService} from "../../service/product.service";
import {ActivatedRoute} from "@angular/router";
import {ProductDTO} from "../../model/product-dto";

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {
  product: ProductDTO;
  id: number;

  constructor(private productService: ProductService,
              private activatedRoute: ActivatedRoute) {

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
}
