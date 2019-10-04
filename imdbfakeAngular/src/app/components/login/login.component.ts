import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string;
  password: string;
  registeredCompleted:boolean = false

  constructor(private route: ActivatedRoute, private authService: AuthService, private router: Router) { }

  ngOnInit() {
    console.log("I am in login")
    this.route.queryParams.subscribe(params =>{
      this.registeredCompleted = params['registeredCompleted']
    })
  }

login(){
  this.authService.login(this.username,this.password).subscribe(response =>{

     console.log(response)
      localStorage.setItem("token", response.access_token)
      localStorage.setItem("username", response.username)
      localStorage.setItem("userId", response.userId)
      localStorage.setItem("expiresIn", response.expires_in)
      this.router.navigate(['movies']);
  })
}

}
