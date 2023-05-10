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

  updateToCart(quantityUpdate: number, idCartDetail: number,email:string):Observable<any> {
    debugger
    // @ts-ignore
    return this.httpClient.put<any>('http://localhost:8080/api/user/cart/updateCart?amount='+quantityUpdate+'&id='+idCartDetail+'&email='+email)
  }

  buyNowCart( userId: number, email: string) {
    // @ts-ignore
    return  this.httpClient.post<any>('http://localhost:8080/api/user/cart/buyNowCart?email='+email+'&idProduct='+userId);

  }

  removeCart(idProduct: number,email:string):Observable<any> {
    return this.httpClient.delete<any>('http://localhost:8080/api/user/cart/deleteCart?email='+email+'&idProduct='+idProduct)
  }

payCart():Observable<any>{
    // @ts-ignore
  return  this.httpClient.put<any>('http://localhost:8080/api/user/cart/payCart')
}
historyOrder():Observable<any>{
    return  this.httpClient.get('http://localhost:8080/api/user/cart/historyOrder')
}
}
