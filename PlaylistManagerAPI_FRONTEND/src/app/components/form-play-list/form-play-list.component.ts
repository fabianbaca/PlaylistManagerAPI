import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { PlayList } from 'src/app/models/PlayList';
import { Song } from 'src/app/models/Song';
import { PlaylistService } from 'src/app/service/playlist.service';

@Component({
  selector: 'app-form-play-list',
  templateUrl: './form-play-list.component.html',
  styleUrl: './form-play-list.component.css'
})
export class FormPlayListComponent {

  constructor(private router: Router, private playlistService: PlaylistService) {}

  playlist = {
    name: '',
    description: '',
    songs: [
      { title: '', artist: '', album: '', genre: '', year: '' }
    ]
  };

  // Agregar una nueva canción a la lista
  addSong() {
    this.playlist.songs.push({ title: '', artist: '', album: '', genre: '', year: '' });
  }

  // Eliminar una canción de la lista
  removeSong(index: number) {
    this.playlist.songs.splice(index, 1);
  }

  onSubmit() {
    const playlistRequest: PlayList = {
      nombre: this.playlist.name,
      descripcion: this.playlist.description,
      canciones: this.playlist.songs.map(song => new Song(
        song.title,
        song.artist,
        song.album,
        song.genre,
        song.year 
      ))
    };


    this.playlistService.createPlaylist(playlistRequest).subscribe({
      next: (response) => {
        console.log('Playlist guardada con éxito:', response);
        this.router.navigate(['/']); 
      },
      error: (error) => {
        console.error('Error al guardar la playlist:', error);
      }
    });
  }

  goBack() {
    this.router.navigate(['/']); // Redirige al usuario a la página principal o lista de playlists
  }
}
