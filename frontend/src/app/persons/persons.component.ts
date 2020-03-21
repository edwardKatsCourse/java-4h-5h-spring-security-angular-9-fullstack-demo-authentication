import { Component, OnInit } from '@angular/core';
import Person from "../person.model";
import {PersonHttpService} from "../person-http.service";

@Component({
  selector: 'app-persons',
  templateUrl: './persons.component.html',
  styleUrls: ['./persons.component.css']
})
export class PersonsComponent implements OnInit {

  public persons: Array<Person> = new Array<Person>();

  constructor(private personHttpService: PersonHttpService) { }

  ngOnInit(): void {
    this.personHttpService.getAllPersons()
      .subscribe(body => this.persons = body);
  }

}
