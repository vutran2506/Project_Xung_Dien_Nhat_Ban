import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

import {Cart} from "../model/cart";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private httpClient:HttpClient) { }
  getCart(email:string):Observable<Cart[]>{
    return this.httpClient.get<Cart[]>('http://localhost:8080/api/user/cart/list/'+email)
  }

  addToCart(value: number, id: number, email: string):Observable<any>{

    // @ts-ignore
    return  this.httpClient.post<any>('http://localhost:8080/api/user/cart/addCart?email='+email+'&amount='+value+'&idProduct='+id);
  }
}
