import { TestBed } from '@angular/core/testing';

import { PlaylistService } from './service/playlist.service';

describe('PlaylistService', () => {
  let service: PlaylistService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PlaylistService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
