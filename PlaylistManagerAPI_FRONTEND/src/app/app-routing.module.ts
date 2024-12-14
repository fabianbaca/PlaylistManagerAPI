import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PlaylistComponent } from './components/playlist/playlist.component';
import { FormPlayListComponent } from './components/form-play-list/form-play-list.component';
import { SongDetailComponent } from './components/song-detail/song-detail.component';

const routes: Routes = [
  { path: '', component: PlaylistComponent },
  { path: 'form-playlist', component: FormPlayListComponent },
  { path: 'song-detail', component: SongDetailComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],  // Configura las rutas
  exports: [RouterModule]                    // Expone RouterModule para ser utilizado en otros módulos
})
export class AppRoutingModule { }
