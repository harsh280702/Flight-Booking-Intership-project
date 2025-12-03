
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { from } from 'rxjs';


interface User {
  email: string;
  password: string;
  role: 'admin' | 'user';
  username: string; // for display purposes
}
@Component({
  selector: 'app-login',
  standalone:true,
  imports: [CommonModule,FormsModule ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  email = '';
  pa = '';  // password variable matching your template ngModel
  userType: 'admin' | 'user' = 'user';  // default user type, can be toggled as needed

  errorMessage = '';
  loggedInUser: User | null = null;

  // Mock user data in JSON format
  MOCK_USERS: User[] = [
    { email: 'admin@example.com', password: 'admin123', role: 'admin', username: 'AdminUser' },
    { email: 'user1@example.com', password: 'user1234', role: 'user', username: 'User One' },
    { email: 'user2@example.com', password: 'user5678', role: 'user', username: 'User Two' },
  ];

  onLogin() {
    this.errorMessage = '';
    this.loggedInUser = null;

    // Simple validation (you can extend this if needed)
    if (!this.email || !this.pa) {
      this.errorMessage = 'Please enter email and password.';
      return;
    }

    // Find user with matching email, password, and role
    const user = this.MOCK_USERS.find(
      u => u.email === this.email && u.password === this.pa && u.role === this.userType
    );

    if (user) {
      this.loggedInUser = user;
      this.errorMessage = '';
    } else {
      this.errorMessage = 'Invalid credentials or user role.';
    }
  }

  // Optional: function to toggle userType if you want role toggle buttons
  setUserType(type: 'admin' | 'user') {
    this.userType = type;
  }
}

