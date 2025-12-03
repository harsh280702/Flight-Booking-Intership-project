import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
export interface FlightRating {
  flightNumber: string;
  airline: string;
  rating: number; // from 1 to 5
  comment: string;
}
@Component({
  selector: 'app-rating',
  imports: [],
  templateUrl: './rating.component.html',
  styleUrl: './rating.component.css'
})
export class RatingComponent {
 ratings: FlightRating[] = [];


  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.loadFlightRatings();
  }

   loadFlightRatings(): void {
    this.ratings = [
      {
        flightNumber: 'AI202',
        airline: 'Air India',
        rating: 4,
        comment: 'Smooth flight and great service.'
      },
      {
        flightNumber: '6E301',
        airline: 'IndiGo',
        rating: 5,
        comment: 'Very punctual and clean aircraft.'
      },
      {
        flightNumber: 'UK108',
        airline: 'Vistara',
        rating: 3,
        comment: 'Comfortable seats but average food.'
      },
      {
        flightNumber: 'SG401',
        airline: 'SpiceJet',
        rating: 2,
        comment: 'Delayed and cramped seating.'
      },
      {
        flightNumber: 'G8123',
        airline: 'Go First',
        rating: 4,
        comment: 'Good value for money.'
      }
    ];
  }
}
