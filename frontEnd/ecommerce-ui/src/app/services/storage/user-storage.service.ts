import { Injectable } from '@angular/core';

const TOKEN = 'temp-token';
const USER = 'temp-user';


@Injectable({
  providedIn: 'root'
})
export class UserStorageService {



  constructor() { }


  public saveToken(token:string): void{
    window.localStorage.removeItem(TOKEN);
    window.localStorage.setItem(TOKEN, token);
  }


  public saveUser(user): void{
    window.localStorage.removeItem(USER);
    window.localStorage.setItem(USER, JSON.stringify(user));
  }



}
