import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ProductType} from "../model/product-type";

@Injectable({
  providedIn: 'root'
})
export class ProductTypeService {

  constructor(private httpClient: HttpClient) {
  }

  getAllProductType(): Observable<ProductType[]> {
    return this.httpClient.get<ProductType[]>('http://localhost:8080/api/user/product/productType')
  }
}
