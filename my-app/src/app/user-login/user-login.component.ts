import { Component, OnInit } from '@angular/core';
import { LoginUserService } from '../login-user.service';
import { User } from '../user';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit{
  user:User = new User();

  constructor(private loginUserService: LoginUserService) {

  }
  ngOnInit(): void {
  }

  userLogin(){
    console.log("user login component: ", this.user);
    this.loginUserService.loginUser(this.user).subscribe(
      (data) => {
      alert("Login success!");
      console.log(data);
    });
  }
}
