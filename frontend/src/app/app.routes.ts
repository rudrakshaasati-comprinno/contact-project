import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AddContactComponent } from './add-contact/add-contact.component';
import { EditContactComponent } from './edit-contact/edit-contact.component';
import { ProfileComponent } from './profile/profile.component';
// ... import others

export const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'dashboard', component: DashboardComponent },  
  { path: 'add-contact', component: AddContactComponent },
  { path: 'edit-contact/:id', component: EditContactComponent },
  { path: 'profile', component: ProfileComponent },
  { path: '**', redirectTo: '/login' }
];