import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environment/environment ';
import { PlayList } from '../models/PlayList';


@Injectable({
  providedIn: 'root'
})
export class PlaylistService {
  
  private apiUrl = `${environment.API_URL}/api/quipux-playlist/lists`; 

  constructor(private http: HttpClient) { }

    private getAuthToken(): string | null {
      return localStorage.getItem('authToken');
    }
    
  
    private getHeaders(): HttpHeaders {
      const token = this.getAuthToken();
      const headers = token ? new HttpHeaders({
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      }) : new HttpHeaders();
    
      return headers;
    }
    

  getPlaylists(): Observable<any> {
    const headers = this.getHeaders();
    return this.http.get(this.apiUrl, { headers });
  }

  createPlaylist(playlist: PlayList): Observable<any> {
    const headers = this.getHeaders();
    return this.http.post(this.apiUrl, playlist, {
      headers: this.getHeaders()
    });
  }

  deletePlaylist(nombre: string): Observable<any> {
    const deleteUrl = `${this.apiUrl}/${nombre}`;
    const headers = this.getHeaders();
    return this.http.delete(deleteUrl, { headers });
  }

  getPlaylistByName(playlistName: string): Observable<any> {
    const url = `${this.apiUrl}/${playlistName}`; 
    const headers = this.getHeaders();
    return this.http.get(url, { headers });
  }
}
