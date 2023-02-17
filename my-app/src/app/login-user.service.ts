import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class LoginUserService {
  private baseUrl = environment.baseUrl;

  constructor(private httpClient: HttpClient) { }

  // send HTTP POST request to backend
  loginUser(user: User): Observable<object> {
    return this.httpClient.post(`${this.baseUrl}/login`, user);
  }
}
