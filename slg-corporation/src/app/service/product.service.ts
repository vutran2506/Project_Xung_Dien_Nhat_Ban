import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ProductDTO} from "../model/product-dto";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient:HttpClient) {
  }
getAllPageProduct(name:string,origin:number,page:number,pageSize:number):Observable<ProductDTO[]>{
return this.httpClient.get<ProductDTO[]>('http://localhost:8080/api/user/product/list');
}
findProductBbyId(id:number):Observable<ProductDTO>{
    return this.httpClient.get<ProductDTO>('http://localhost:8080/api/user/product/detail/'+id)
}
}
export interface Page<T> {
  content: T[];
  pageable: {
    sort: {
      sorted: boolean;
      unsorted: boolean;
    };
    pageNumber: number;
    pageSize: number;
    offset: number;
    unpaged: boolean;
  };
  totalPages: number;
  totalElements: number;
  last: boolean;
  size: number;
  number: number;
  sort: {
    sorted: boolean;
    unsorted: boolean;
  };
  numberOfElements: number;
  first: boolean;
  empty: boolean;
}

