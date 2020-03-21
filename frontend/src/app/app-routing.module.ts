import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {PersonsComponent} from "./persons/persons.component";
import {AuthGuardGuard} from "./login/auth-guard.guard";
import {LogoutComponent} from "./logout/logout.component";


const routes: Routes = [
  {
    path: 'login', component: LoginComponent
  },

  {
    path: 'persons', component: PersonsComponent, canActivate: [AuthGuardGuard]
  },
  {
    path: 'logout', component: LogoutComponent, canActivate: [AuthGuardGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
