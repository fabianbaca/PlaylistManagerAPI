import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';  
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { FormPlayListComponent } from './components/form-play-list/form-play-list.component';
import { PlaylistComponent } from './components/playlist/playlist.component';
import { SongDetailComponent } from './components/song-detail/song-detail.component';
import { LoginComponent } from './components/login/login.component';
import { ToastrModule } from 'ngx-toastr';

@NgModule({
  declarations: [
    AppComponent,
    PlaylistComponent,
    FormPlayListComponent,
    LoginComponent,
    SongDetailComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,  
    FormsModule,
    ToastrModule.forRoot(), 
    BrowserAnimationsModule,

  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
