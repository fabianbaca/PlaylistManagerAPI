import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-form-play-list',
  templateUrl: './form-play-list.component.html',
  styleUrl: './form-play-list.component.css'
})
export class FormPlayListComponent {

    constructor(private router: Router ) {}

    playlist = {
      name: '',
      description: '',
      songs: [
        { title: '', artist: '', album: '', genre: '', year: null }
      ]
    };

    // Agregar una nueva canción a la lista
    addSong() {
      this.playlist.songs.push({ title: '', artist: '', album: '', genre: '', year: null });
    }

    // Eliminar una canción de la lista
    removeSong(index: number) {
      this.playlist.songs.splice(index, 1);
    }

    // Función de envío del formulario (aquí puedes agregar la lógica para guardar la playlist)
    onSubmit() {
      console.log('Playlist creada:', this.playlist);
      // Aquí puedes agregar la lógica para guardar la playlist (llamar a un servicio, etc.)
    }

    goBack() {
      this.router.navigate(['/']); // Redirige al usuario a la página principal o lista de playlists
    }
}
