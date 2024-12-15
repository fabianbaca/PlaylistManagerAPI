import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AlertService } from 'src/app/alert.service';
import { PlaylistService } from 'src/app/service/playlist.service';

@Component({
  selector: 'app-playlist',
  templateUrl: './playlist.component.html',
  styleUrl: './playlist.component.css'
})
export class PlaylistComponent {

  searchTerm: string = '';
  playlists: any[] = [];
  filteredPlaylists = this.playlists;  

  constructor(
    private playlistService: PlaylistService,
    private router: Router,
    private alertService: AlertService
  ) {}

  ngOnInit(): void {
    this.loadPlaylists(); 
  }


  loadPlaylists(): void {
    this.playlistService.getPlaylists().subscribe({
      next: (response) => {
        this.playlists = response;
        this.filteredPlaylists = this.playlists;
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
        }
      });
    }
  }

  goToSongDetail(playlist: any): void {
    this.router.navigate(['/song-detail'], {
      queryParams: { data: JSON.stringify(playlist) }
    });
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
      },
      error: (error) => {
        this.alertService.showError('Error: ' + (error?.error?.message || 'Ha ocurrido un error desconocido.'));
      }
    });
  }
  

}
