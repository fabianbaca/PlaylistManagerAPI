import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environment/environment ';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

 private apiUrl = `${environment.API_URL}/api/quipux-playlist/auth/login`;

  constructor(private http: HttpClient) { }

  // Método de login
  login(username: string, password: string): Observable<any> {
    const url = `${this.apiUrl}?username=${username}&password=${password}`;
    return this.http.post(url, null);
  }

  // Verificar si el usuario está autenticado
  isAuthenticated(): boolean {
    return !!localStorage.getItem('authToken');  // Si tienes un token en localStorage, el usuario está autenticado
  }

  // Cerrar sesión
  logout(): void {
    localStorage.removeItem('authToken');
  }
}