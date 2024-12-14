import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { PlaylistService } from 'src/app/service/playlist.service';

@Component({
  selector: 'app-playlist',
  templateUrl: './playlist.component.html',
  styleUrl: './playlist.component.css'
})
export class PlaylistComponent {

  searchTerm: string = '';
  playlists: any[] = [];
  filteredPlaylists = this.playlists;  // Lista filtrada inicializada con todas las playlists

  constructor(
    private playlistService: PlaylistService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadPlaylists();  // Cargar todas las playlists al iniciar
  }

  // Función para cargar todas las playlists
  loadPlaylists(): void {
    this.playlistService.getPlaylists().subscribe({
      next: (response) => {
        this.playlists = response;
        this.filteredPlaylists = this.playlists; // Inicializar la lista filtrada con todas las playlists
      },
      error: (error) => {
        console.error('Error al cargar las playlists:', error);
      }
    });
  }

  filterPlaylists(): void {
    if (this.searchTerm.trim() === '') {
      this.filteredPlaylists = this.playlists;
    } else {
      this.playlistService.getPlaylistByName(this.searchTerm).subscribe({
        next: (response) => {
          this.filteredPlaylists = [response];
        },
        error: (error) => {
          this.filteredPlaylists =[];
          console.error('Error al buscar la playlist:', error);
        }
      });
    }
  }

  goToSongDetail(playlist: any): void {
    this.router.navigate(['/song-detail'], {
      queryParams: { data: JSON.stringify(playlist) }
    });
  }

  onMouseOver(playlist: any) {
    console.log('Mouse sobre: ', playlist);
  }

  onMouseLeave() {
    console.log('Mouse fuera');
  }


  goToFormPlaylist(): void {
    this.router.navigate(['/form-playlist']);
  }


  clearSearch(): void {
    this.searchTerm = '';
    this.filteredPlaylists = this.playlists;  
  }


  deletePlaylist(playlist: any): void {
    this.playlistService.deletePlaylist(playlist.name).subscribe({
      next: () => {
        const index = this.filteredPlaylists.indexOf(playlist);
        if (index !== -1) {
          this.filteredPlaylists.splice(index, 1); 
        }
        console.log('Playlist eliminada');
      },
      error: (error) => {
        console.error('Error al eliminar la playlist:', error);
      }
    });
  }
  

}
