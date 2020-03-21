import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest} from "@angular/common/http";
import Person from "./person.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PersonHttpService implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let token = localStorage.getItem("Authorization");

    if (token) {
      let requestClone = req.clone({
          headers: new HttpHeaders({
            "Authorization" : token
          })
      });
      return next.handle(requestClone);
    }
    return next.handle(req);
  }

  constructor(private httpClient: HttpClient) { }

  register() {

  }

  login(username: string, password: string) {
    //1. CORS - relative path
    //2. Object
    return this.httpClient.post<Person>('/api/login', {username: username, password: password}, {observe: "response"});
  }

  logout() {
    return this.httpClient.post<void>('/api/logout', {});
  }

  //1. Prevent PersonComponent if there is no token - route (Auth Guard)
  //2. Add token for each request

  getAllPersons() {
    return this.httpClient.get<Array<Person>>('/api/persons');
  }
}
