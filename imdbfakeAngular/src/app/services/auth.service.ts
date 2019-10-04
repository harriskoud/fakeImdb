import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../model/User';
import { map } from 'rxjs/operators';
import { Authentication } from '../model/Authentication';
import * as jwt_decode from 'jwt-decode';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpClient: HttpClient,private router: Router) { }

  basicAuth(username, password) {
    if (username === "javainuse" && password === "password") {
      sessionStorage.setItem('username', username)
      //call login endpoint
      return true;
    } else {
      return false;
    }
  }

  isUserLoggedIn() {
    let user = localStorage.getItem('username')
    console.log(!(user === null))
    return !(user === null)
  }

  logOut() {
    localStorage.removeItem('username')
    localStorage.removeItem('token')
    localStorage.removeItem('userId')
    localStorage.removeItem('expiresIn')
    this.router.navigate([''])
  }

  register(user: User) {
    return this.httpClient.post("http://localhost:8090/user", user)
  }


  login(username, password) {

    let formData: FormData = new FormData();
    formData.append('grant_type', "password");
    formData.append('scope', "webclient");
    formData.append('username', username);
    formData.append('password', password);

    return this.httpClient.post<Authentication>("http://localhost:8901/oauth/token", formData,
      {
        headers: new HttpHeaders({
          "Authorization": "Basic " + btoa("user-microservice:thisissecret")
        })
      })
  }


  createBasicAuthHeader() {
    var headers_object = new HttpHeaders();
    headers_object.append("Authorization", "Basic " + btoa("user-microservice:thisissecret"));

    return headers_object;
  }

  public isAuthenticated(): boolean {
    const token = localStorage.getItem('token');
    return  this.isTokenExpired(token)
  }

  isTokenExpired(token?: string): boolean {
    if(!token) return true;

    const date = this.getTokenExpirationDate(token);
    if(date === undefined) return false;
    return !(date.valueOf() > new Date().valueOf());
  }

  getTokenExpirationDate(token: string): Date {
    const decoded = jwt_decode(token);

    if (decoded.exp === undefined) return null;

    const date = new Date(0); 
    date.setUTCSeconds(decoded.exp);
    return date;
  }


}
