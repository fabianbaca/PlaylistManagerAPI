import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-playlist',
  templateUrl: './playlist.component.html',
  styleUrl: './playlist.component.css'
})
export class PlaylistComponent {

  searchTerm: string = '';
  playlists = [
    {
      id: 1,
      name: 'Playlist 1',
      description: 'Una lista de canciones geniales.',
      songs: [
        { title: 'Canción 1', artist: 'Artista 1', album: 'Álbum 1', genre: 'Pop', year: 2020 },
        { title: 'Canción 2', artist: 'Artista 2', album: 'Álbum 2', genre: 'Rock', year: 2019 },
        { title: 'Canción 3', artist: 'Artista 3', album: 'Álbum 3', genre: 'Jazz', year: 2021 }
      ]
    },
    {
      id: 2,
      name: 'Playlist 2',
      description: 'Otra lista, con más canciones.',
      songs: [
        { title: 'Canción A', artist: 'Artista A', album: 'Álbum A', genre: 'Reggaetón', year: 2018 },
        { title: 'Canción B', artist: 'Artista B', album: 'Álbum B', genre: 'Hip Hop', year: 2020 },
        { title: 'Canción C', artist: 'Artista C', album: 'Álbum C', genre: 'Electrónica', year: 2021 }
      ]
    },
    {
      id: 3,
      name: 'Playlist 3',
      description: 'Una lista de música tranquila y relajante.',
      songs: [
        { title: 'Canción X', artist: 'Artista X', album: 'Álbum X', genre: 'Clásica', year: 2015 },
        { title: 'Canción Y', artist: 'Artista Y', album: 'Álbum Y', genre: 'Ambient', year: 2017 },
        { title: 'Canción Z', artist: 'Artista Z', album: 'Álbum Z', genre: 'New Age', year: 2019 }
      ]
    }
  ];
  
  
  filteredPlaylists = this.playlists;  // Lista filtrada inicializada con todas las playlists

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.filterPlaylists();  // Filtrar al inicio por defecto
  }

  // Función para filtrar las playlists según el término de búsqueda
  filterPlaylists(): void {
    this.filteredPlaylists = this.playlists.filter(playlist => 
      playlist.name.toLowerCase().includes(this.searchTerm.toLowerCase()) || 
      playlist.description.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

  // Función para ir a la vista de detalles
  goToSongDetail(playlist: any): void {
    this.router.navigate(['/song-detail'], {
      queryParams: { data: JSON.stringify(playlist) }
    });
  }

  // Función de hover en la tarjeta
  onMouseOver(playlist: any) {
    console.log('Mouse sobre: ', playlist);
  }

  onMouseLeave() {
    console.log('Mouse fuera');
  }

  // Función de creación de lista de reproducción
  goToFormPlaylist(): void {
    this.router.navigate(['/form-playlist']);
  }

  // Función para limpiar el campo de búsqueda y mostrar todas las playlists
  clearSearch(): void {
    this.searchTerm = '';
    this.filteredPlaylists = this.playlists;  // Restablecer la lista filtrada
  }


  deletePlaylist(playlist: any): void {
    const index = this.filteredPlaylists.indexOf(playlist);
    if (index !== -1) {
      this.filteredPlaylists.splice(index, 1);  // Eliminar la playlist
    }
  }
  

}
