import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PlaylistComponent } from './components/playlist/playlist.component';
import { FormPlayListComponent } from './components/form-play-list/form-play-list.component';
import { SongDetailComponent } from './components/song-detail/song-detail.component';
import { LoginComponent } from './components/login/login.component';
import { AuthGuard } from './guard/auth.guard';  // Crea un guardia para proteger las rutas

const routes: Routes = [
  { path: '', component: PlaylistComponent, canActivate: [AuthGuard] }, // Ruta protegida
  { path: 'form-playlist', component: FormPlayListComponent, canActivate: [AuthGuard] }, // Ruta protegida
  { path: 'song-detail', component: SongDetailComponent, canActivate: [AuthGuard] }, // Ruta protegida
  { path: 'login', component: LoginComponent },
  { path: '**', redirectTo: 'login' }  // Redirigir a login si la ruta no existe
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
