import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environment/environment ';
import { PlayList } from '../models/PlayList';


@Injectable({
  providedIn: 'root'
})
export class PlaylistService {
  
  private apiUrl = `${environment.API_URL}/api/quipux-playlist/lists`; // URL base de las playlists

  constructor(private http: HttpClient) { }

    // Obtener el token del localStorage
    private getAuthToken(): string | null {
      return localStorage.getItem('authToken');
    }
    
  
    // Configurar los headers con el token
    private getHeaders(): HttpHeaders {
      const token = this.getAuthToken();
      const headers = token ? new HttpHeaders({
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      }) : new HttpHeaders();
    
      console.log('Headers configurados:', headers); // Verifica si el encabezado es correcto
      return headers;
    }
    

  // Método GET para obtener todas las playlists con token en los headers
  getPlaylists(): Observable<any> {
    const headers = this.getHeaders();
    return this.http.get(this.apiUrl, { headers });
  }

  // Método POST para crear una nueva playlist con token en los headers
  createPlaylist(playlist: PlayList): Observable<any> {
    const headers = this.getHeaders();
    console.log(headers);
    return this.http.post(this.apiUrl, playlist, {
      headers: this.getHeaders()
    });
  }

  // Método DELETE para eliminar una playlist por nombre con token en los headers
  deletePlaylist(nombre: string): Observable<any> {
    const deleteUrl = `${this.apiUrl}/${nombre}`;
    const headers = this.getHeaders();
    return this.http.delete(deleteUrl, { headers });
  }

  // Nuevo método GET para obtener una playlist por nombre con token en los headers
  getPlaylistByName(playlistName: string): Observable<any> {
    const url = `${this.apiUrl}/${playlistName}`; // Aquí se inserta el nombre de la playlist en la URL
    const headers = this.getHeaders();
    return this.http.get(url, { headers });
  }
}
