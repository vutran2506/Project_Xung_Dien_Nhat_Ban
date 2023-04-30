import { Component, OnInit } from '@angular/core';
import Swal from "sweetalert2";
import {Router} from "@angular/router";
import {ShareService} from "../../../security-authentication/service/share.service";
import {TokenStorageService} from "../../../security-authentication/service/token-storage.service";

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

  constructor(private tokenStorageService: TokenStorageService,
              private shareService: ShareService,
              private router: Router) {
  }

  loadHeader(): void {
    if (this.tokenStorageService.getToken()) {
      this.currentUser = this.tokenStorageService.getUser().username;
      this.role = this.tokenStorageService.getUser().roles[0];
      this.username = this.tokenStorageService.getUser().username;
      debugger
    }
    this.isLoggedIn = this.username != null;
    this.getUsernameAccount();
  }


  ngOnInit(): void {
    this.shareService.getClickEvent().subscribe(() => {
      this.loadHeader();
    });
    this.loadHeader();
  }

  async logOut() {
    this.tokenStorageService.signOut();
    this.shareService.sendClickEvent();
    await Swal.fire({
      text: 'Đăng xuất thành công',
      icon: 'success',
      showConfirmButton: false,
      timer: 1500
    });
    await this.router.navigateByUrl('');
    location.reload();
  }

  getUsernameAccount() {
    if (this.tokenStorageService.getToken()) {
      this.nameUser = this.tokenStorageService.getUser().name;

      this.emailUser = this.tokenStorageService.getUser().email;
    }
  }

}
