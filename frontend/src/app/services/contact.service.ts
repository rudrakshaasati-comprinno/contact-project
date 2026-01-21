import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ContactService {

  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}


  // GET all contacts
  getAllContacts(): Observable<any> {
    return this.http.get(`${this.baseUrl}/contacts`);
  }

  // GET contact by ID
  getContactById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/contacts/${id}`);
  }

  // POST create new contact
  createContact(data: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/contacts`, data);
  }

  // PUT update contact
  updateContact(id: number, data: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/contacts/${id}`, data);
  }

  // DELETE contact
  deleteContact(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/contacts/${id}`);
  }
}
