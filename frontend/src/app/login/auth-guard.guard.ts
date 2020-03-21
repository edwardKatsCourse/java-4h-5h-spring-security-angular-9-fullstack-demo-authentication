import { Injectable } from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router} from '@angular/router';
import { Observable } from 'rxjs';
import {PersonHttpService} from "../person-http.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuardGuard implements CanActivate {


  constructor(private router: Router, private personHttpService: PersonHttpService) {
  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    let token = localStorage.getItem("Authorization");

    let destinationUrl = state.url;
    if (destinationUrl.includes('logout')) {
      this.personHttpService.logout().subscribe();
      localStorage.clear();
      this.router.navigate(['/login']);
      return true;
    }

    if (!token) {
      this.router.navigate(['/login']);
      return false;
    }


    return true;
  }

}
