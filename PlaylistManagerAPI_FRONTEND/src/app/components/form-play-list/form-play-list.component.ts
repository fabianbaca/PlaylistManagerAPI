import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AlertService } from 'src/app/alert.service';
import { PlayList } from 'src/app/models/PlayList';
import { Song } from 'src/app/models/Song';
import { PlaylistService } from 'src/app/service/playlist.service';

@Component({
  selector: 'app-form-play-list',
  templateUrl: './form-play-list.component.html',
  styleUrl: './form-play-list.component.css'
})
export class FormPlayListComponent {

  constructor(private router: Router, private playlistService: PlaylistService, private alertService: AlertService) {}

  playlist = {
    name: '',
    description: '',
    songs: [
      { title: '', artist: '', album: '', genre: '', year: '' }
    ]
  };

  get hasSongWithTitle() {
    return this.playlist.songs.some(song => song.title);
  }

  addSong() {
    this.playlist.songs.push({ title: '', artist: '', album: '', genre: '', year: '' });
  }

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
        this.router.navigate(['/']); 
        this.alertService.showSuccess('¡Lista de reproducción creada con éxito!');
      },
      error: (error) => {
        this.alertService.showError('Error: ' + (error?.error?.message || 'Ha ocurrido un error desconocido.'));
      }
    });
  }

  goBack() {
    this.router.navigate(['/']); 
  }

}
