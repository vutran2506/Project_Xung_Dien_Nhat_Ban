import { Component, OnInit } from '@angular/core';
import Swal from "sweetalert2";
import {Router} from "@angular/router";
import {ShareService} from "../../../security-authentication/service/share.service";
import {TokenStorageService} from "../../../security-authentication/service/token-storage.service";
import {ShareDataService} from "../../../service/share-data.service";
import {LoginService} from "../../../security-authentication/service/login.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  username: string;
  nameUser:string;
  currentUser: string;
 emailUser:string;
  role: string;
  isLoggedIn = false;
 totalProduct: number;

  constructor(private tokenStorageService: TokenStorageService,
              private shareService: ShareService,
              private shareDataService: ShareDataService,
              private router: Router,
             ) {
  }

  loadHeader(): void {
    if (this.tokenStorageService.getToken()) {
      this.currentUser = this.tokenStorageService.getUser().username;
      this.role = this.tokenStorageService.getUser().roles[0];
      this.username = this.tokenStorageService.getUser().username;
    }
    this.isLoggedIn = this.username != null;
    this.getUsernameAccount();
    this.shareDataService.getTotalProduct().subscribe(totalProduct => {
      this.totalProduct = totalProduct;
    });
  }


  ngOnInit(): void {
    this.shareService.getClickEvent().subscribe(() => {
      this.loadHeader();
    });
    this.loadHeader();
  }

  async logOut() {
    this.tokenStorageService.signOut();

    await Swal.fire({
      text: 'Đăng xuất thành công',
      icon: 'success',
      showConfirmButton: false,
      timer: 1500
    });
    this.isLoggedIn = false;
    await this.router.navigateByUrl('');
  }

  getUsernameAccount() {
    if (this.tokenStorageService.getToken()) {
      this.nameUser = this.tokenStorageService.getUser().name;

      this.emailUser = this.tokenStorageService.getUser().email;
    }
  }

}
