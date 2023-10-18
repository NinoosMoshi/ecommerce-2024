import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth/auth.service';
import { UserStorageService } from '../services/storage/user-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginForm!:FormGroup;
  hidePassword:boolean = true;

  constructor(private fb:FormBuilder,
              private snackBar: MatSnackBar,
              private authService: AuthService,
              private router: Router){}

  ngOnInit(){
    this.myForm();
  }


  myForm(){
    this.loginForm = this.fb.group({
      email:[null, [Validators.required]],
      password:[null, [Validators.required]]
    })
  }


  togglePasswordVisibility(){
    this.hidePassword = !this.hidePassword;
 }


 onSubmit(){
  const username = this.loginForm.get('email')?.value;
  const password = this.loginForm.get('password')?.value;

  this.authService.login(username, password).subscribe({
    next: res =>{
      if(UserStorageService.isAdminLoggedIn()){
        this.router.navigateByUrl("/admin/dashboard")
      }
      else if(UserStorageService.isCustomerLoggedIn()){
        this.router.navigateByUrl("/customer/dashboard")
      }
    },
    error: err =>{
      this.snackBar.open('Bad credentials.', 'ERROR', { duration: 5000});
    }
  })

 }



}
