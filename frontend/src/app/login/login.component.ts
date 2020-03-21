import {Component, OnInit} from "@angular/core";
import {PersonHttpService} from "../person-http.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public person: { username: string, password: string } = {username: '', password: ''};

  constructor(private personHttpService: PersonHttpService, private route: Router) {
  }

  ngOnInit(): void {
  }

  login() {
    this.personHttpService
      .login(this.person.username, this.person.password)
      .subscribe(response => {
        localStorage.setItem("Authorization", response.headers.get("Authorization"));
        this.route.navigate(['/persons']);
      })
  }

}
