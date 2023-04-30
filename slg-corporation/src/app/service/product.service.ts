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
getAllPageProduct(name:string,page:number,pageSize:number):Observable<ProductDTO[]>{
return this.httpClient.get<ProductDTO[]>('http://localhost:8080/api/public/product/list?page='+page+'&size='+pageSize+'&name='+name);
}
findProductBbyId(id:number):Observable<ProductDTO>{
    return this.httpClient.get<ProductDTO>('http://localhost:8080/api/public/product/detail/'+id)
}

}

