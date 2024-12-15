import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-song-detail',
  templateUrl: './song-detail.component.html',
  styleUrl: './song-detail.component.css'
})
export class SongDetailComponent {

  songDetails: any;

  constructor(private route: ActivatedRoute,   private router: Router ) {}

  ngOnInit(): void {
    const data = this.route.snapshot.queryParamMap.get('data');

    if (data) {
      this.songDetails = JSON.parse(data); 
    }
  }

  goBack() {
    this.router.navigate(['/']); 
  }

}
