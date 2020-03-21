import { TestBed } from '@angular/core/testing';

import { PersonHttpService } from './person-http.service';

describe('PersonHttpService', () => {
  let service: PersonHttpService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PersonHttpService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
