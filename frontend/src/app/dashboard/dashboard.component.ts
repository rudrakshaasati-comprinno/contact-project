import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NavbarComponent } from '../layout/navbar/navbar.component';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, FormsModule, NavbarComponent],

  
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {

  contacts: any[] = [];
  loading = false;
  errorMessage = '';

  newContact = {
    name: '',
    email: '',
    phone: ''
  };
  page = 0;
size = 5;          // contacts per page
totalPages = 0;

editing = false;
editingId: number | null = null;

  private API_URL = 'http://localhost:8080/api/contacts';

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.loadContacts();
  }

  loadContacts(): void {
  this.http.get<any>(
    `${this.API_URL}?page=${this.page}&size=${this.size}`
  ).subscribe({
    next: res => {
      this.contacts = res.content;
      this.totalPages = res.totalPages;
    },
    error: () => {
      this.errorMessage = 'Failed to load contacts';
    }
  });
}
nextPage(): void {
  if (this.page < this.totalPages - 1) {
    this.page++;
    this.loadContacts();
  }
}

previousPage(): void {
  if (this.page > 0) {
    this.page--;
    this.loadContacts();
  }
}


saveContact(): void {

  if (this.editing) {
    // UPDATE
    this.http.put(`${this.API_URL}/${this.editingId}`, this.newContact)
      .subscribe({
        next: () => {
          this.resetForm();

          // ✅ ADD THESE 2 LINES
          this.page = 0;
          this.loadContacts();

          this.closeModal();
        },
        error: err => {
          console.error('Update failed', err);
          alert('Update failed');
        }
      });
  } else {
    // ADD
    this.http.post(this.API_URL, this.newContact)
      .subscribe({
        next: () => {
          this.resetForm();

          // ✅ ADD THESE 2 LINES
          this.page = 0;
          this.loadContacts();

          this.closeModal();
        },
        error: err => {
          console.error('Add failed', err);
          alert('Add failed');
        }
      });
  }
}


deleteContact(id: number): void {
  if (!confirm('Are you sure you want to delete this contact?')) {
    return;
  }

  this.http.delete(`${this.API_URL}/${id}`, { responseType: 'text' })
    .subscribe({
      next: () => {

        // ✅ ADD THESE 2 LINES
        this.page = 0;
        this.loadContacts();

      },
      error: err => {
        console.error('Delete failed', err);
        alert('Delete failed. Please refresh and try again.');
      }
    });
}



openEditModal(contact: any): void {
  this.editing = true;
  this.editingId = contact.id;
  this.newContact = { ...contact };

  const modal = new (window as any).bootstrap.Modal(
    document.getElementById('addContactModal')
  );
  modal.show();
}

resetForm(): void {
  this.newContact = { name: '', email: '', phone: '' };
  this.editing = false;
  this.editingId = null;
}

closeModal(): void {
  const modalEl = document.getElementById('addContactModal');
  if (modalEl) {
    (window as any).bootstrap.Modal.getInstance(modalEl)?.hide();
  }
}


 
}
