import { Component, OnInit } from '@angular/core';
import {CartService} from "../../service/cart.service";
import {Orders} from "../../model/orders";

@Component({
  selector: 'app-history-payment',
  templateUrl: './history-payment.component.html',
  styleUrls: ['./history-payment.component.css']
})
export class HistoryPaymentComponent implements OnInit {
  listOrderHistory: Orders[];
  showDetail: any;

  constructor(private cartService:CartService) { }

  ngOnInit(): void {
    this.cartService.historyOrder().subscribe(data=>{
      this.listOrderHistory = data;

    })
    this.view()
  }
  view(): void {
    const element = document.getElementById('historyOrder');
    if (element) {
      element.scrollIntoView();
    }
  }

  showOrderDetail(order: Orders) {

  }
}
