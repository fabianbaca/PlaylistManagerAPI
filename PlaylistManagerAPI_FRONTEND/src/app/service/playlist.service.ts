import { HttpClient } from '@angular/common/http';
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

  // Método GET para obtener todas las playlists
  getPlaylists(): Observable<any> {
    return this.http.get(this.apiUrl);
  }

  // Método POST para crear una nueva playlist
  createPlaylist(playlist: PlayList): Observable<any> {
    return this.http.post(this.apiUrl, playlist);
  }

  // Método DELETE para eliminar una playlist por id
  deletePlaylist(nombre: string): Observable<any> {
    const deleteUrl = `${this.apiUrl}/${nombre}`;
    return this.http.delete(deleteUrl);
  }

  // Nuevo método GET para obtener una playlist por nombre
  getPlaylistByName(playlistName: string): Observable<any> {
    const url = `${this.apiUrl}/${playlistName}`; // Aquí se inserta el nombre de la playlist en la URL
    return this.http.get(url);
  }
}
