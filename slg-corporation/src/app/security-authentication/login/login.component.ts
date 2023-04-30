import {Component, DoCheck, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import Swal from 'sweetalert2';
import {LoginService} from '../service/login.service';
import {Router} from '@angular/router';
import {TokenStorageService} from '../service/token-storage.service';
import {ShareService} from '../service/share.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  username: string;
  // errorMessage = '';
  roles: string[] = [];
  returnUrl: string;

  constructor(private loginService: LoginService,
              private router: Router,
              private tokenStorageService: TokenStorageService,
              private shareService: ShareService) {
  }

  ngOnInit(): void {
    if (this.tokenStorageService.getToken()) {
      const user = this.tokenStorageService.getUser();
      this.loginService.isLoggedIn = true;
      this.roles = this.tokenStorageService.getUser().roles;
      this.username = this.tokenStorageService.getUser().username;
    }
    if(this.loginService.isLoggedIn) {
      Swal.fire({
        text: 'Bạn đã đăng nhập.',
        icon: 'warning',
        iconColor: 'darkorange',
        showConfirmButton: false,
        timer: 1500
      });
      this.router.navigateByUrl('/product');
    }
    this.view();
    this.loginForm = new FormGroup({
      username: new FormControl('',[Validators.email, Validators.required]),
      password: new FormControl('',[Validators.required]),
      rememberMe: new FormControl()
    });

  }


  view(): void {
    const element = document.getElementById('login');
    if (element) {
      element.scrollIntoView();
    }
  }

  onSubmit() {
    this.loginService.login(this.loginForm.value).subscribe(
      data => {
        debugger
        if (this.loginForm.value.rememberMe) {
          this.tokenStorageService.saveTokenLocal(data.accessToken);
          this.tokenStorageService.saveUserLocal(data);
        } else {
          this.tokenStorageService.saveTokenSession(data.accessToken);
          this.tokenStorageService.saveUserLocal(data);
        }

        this.loginService.isLoggedIn = true;
        this.username = this.tokenStorageService.getUser().username;
        this.roles = this.tokenStorageService.getUser().roles;
        this.loginForm.reset();
        Swal.fire({
          text: 'Đăng nhập thành công',
          icon: 'success',
          showConfirmButton: false,
          timer: 1500
        });
        this.router.navigateByUrl('');
        this.shareService.sendClickEvent();
      },
      err => {
        // this.errorMessage = err.error.message;
        this.loginService.isLoggedIn = false;
        Swal.fire({
          text: 'Tài khoản, mật khẩu không đúng hoặc chưa được kích hoạt!',
          icon: 'error',
          confirmButtonText: 'OK'
        });
      }
    );
  }

}
