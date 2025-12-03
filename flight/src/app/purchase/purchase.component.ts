import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-purchase',
  imports: [CommonModule],
  templateUrl: './purchase.component.html',
  styleUrl: './purchase.component.css'
})
export class PurchaseComponent {

   purchaseForm: FormGroup;
  flightFare: number = 3000; // Fixed fare per passenger
  totalFare: number = 0;
  showSuccessModal = false;

  constructor(private fb: FormBuilder, private http: HttpClient) {
    this.purchaseForm = this.fb.group({
      passengers: this.fb.array([this.createPassenger()])
    });
    this.calculateTotalFare();
  }

  get passengers(): FormArray {
    return this.purchaseForm.get('passengers') as FormArray;
  }

  createPassenger(): FormGroup {
    return this.fb.group({
      name: ['', Validators.required],
      age: ['', [Validators.required, Validators.min(1)]]
    });
  }

  addPassenger() {
    this.passengers.push(this.createPassenger());
    this.calculateTotalFare();
  }

  removePassenger(index: number) {
    this.passengers.removeAt(index);
    this.calculateTotalFare();
  }

  calculateTotalFare() {
    this.totalFare = this.passengers.length * this.flightFare;
  }

  onSubmit() {
    if (this.purchaseForm.valid) {
      const payload = {
        passengers: this.passengers.value,
        totalFare: this.totalFare,
        flightFare: this.flightFare
      };

      this.http.post('http://localhost:8080/api/bookings', payload).subscribe({
        next: () => {
          this.showSuccessModal = true;
          this.purchaseForm.reset();
          this.passengers.clear();
          this.addPassenger();
          this.calculateTotalFare();
        },
        error: () => alert('Booking failed')
      });
    }
  }

  closeModal() {
    this.showSuccessModal = false;
  }
  bookFlight() {
    alert(`Flight booked:`);
  }
}
