import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppLayoutComponent } from './_layout';
import {AuthGuard} from "./security/auth.guard";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";


const routes: Routes = [ {
  path: '',component: AppLayoutComponent, canActivate: [AuthGuard],
  children: [
    {path: '', pathMatch: 'full', redirectTo: 'home'},
    {path: 'home', loadChildren: './pages/home/home.module#HomeModule'},
  ]
},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
