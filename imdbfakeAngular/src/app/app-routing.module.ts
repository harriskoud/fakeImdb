import { MoviesDashboardComponent } from './components/movies-dashboard/movies-dashboard.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { AuthGuardService } from './services/auth-guard.service';
import { LoginGuardService } from './services/login-guard.service';

const routes: Routes = [
  { path: '', component: LoginComponent , canActivate: [AuthGuardService]},
  { path: 'register', component: RegisterComponent },
  { path: 'movies', component: MoviesDashboardComponent, canActivate:[LoginGuardService]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
