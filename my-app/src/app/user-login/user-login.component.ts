import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginUserService } from '../login-user.service';
import { User } from '../user';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})

export class UserLoginComponent implements OnInit{
  user:User = new User();
  errorMessage: string | undefined;

  constructor(private loginUserService: LoginUserService, private router: Router) {
  }

  ngOnInit(): void {
  }

  userLogin(){
    // create an observer object to handle responses and errors
    const observer = {
      next: (response: any) => {
        // handle the successful response here
        this.router.navigate(['/welcome', response.userName]);
      },
      error: (error: any) => {
        // handle errors here
        console.error('Login failed', error);
        this.errorMessage = 'Invalid username or password';
      }
    };

    this.loginUserService.loginUser(this.user).subscribe(observer);
  }
}
