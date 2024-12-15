import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {


  username: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  // Función para hacer login
  login(): void {
    this.authService.login(this.username, this.password).subscribe({
      next: (response) => {
        localStorage.setItem('authToken', response.token);
        this.router.navigate(['/']);  
      },
      error: (error) => {
        console.error('Error de login', error);
      }
    });
  }



}
