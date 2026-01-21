import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NavbarComponent } from '../layout/navbar/navbar.component';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule, FormsModule, NavbarComponent],
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user: any = {
    name: '',
    email: '',
    password: ''
  };

  API_URL = 'http://localhost:8080/api/users/profile';

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.loadProfile();
  }

  loadProfile() {
    this.http.get<any>(this.API_URL)
      .subscribe(res => {
        this.user.name = res.name;
        this.user.email = res.email;
      });
  }

  updateProfile() {
    this.http.put(this.API_URL, this.user)
      .subscribe(() => {
        alert('Profile updated successfully ✅');
        this.user.password = '';
      });
  }
}
