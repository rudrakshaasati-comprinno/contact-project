import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  login(data: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/users/login`, data);
  }
  register(data: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/users/register`, data);
  }
  // ✅ SAVE TOKEN
  saveToken(token: string): void {
    localStorage.setItem('token', token);
  }

  // ✅ GET TOKEN
  getToken(): string | null {
    return localStorage.getItem('token');
  }

  // ✅ LOGOUT
  logout(): void {
    localStorage.removeItem('token');
  }

  // ✅ CHECK LOGIN
  isLoggedIn(): boolean {
    return !!this.getToken();
  }
}
