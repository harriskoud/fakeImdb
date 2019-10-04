import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/User';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  lastname: string
  firstname: string
  username: string
  password: string
  registeredCompleted:boolean = false
  
  constructor(private authService: AuthService,private router: Router) { }

  ngOnInit() {
  }

  register(){
    var user = new User(this.firstname,this.lastname,this.username,this.password)
    this.authService.register(user).subscribe(response => {
      this.registeredCompleted = true
      this.router.navigate([''], { queryParams: { registeredCompleted: 'true' } });
      console.log(response)
    },err=>{
      console.log(err)
    })
  }
}
