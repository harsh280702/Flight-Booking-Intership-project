import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { SeacrflightComponent } from "../seacrflight/seacrflight.component";
import { WhychooseusComponent } from '../whychooseus/whychooseus.component';
import { ServiceComponent } from "../service/service.component";
import { RouterLink, RouterLinkActive } from '@angular/router';
import { RatingComponent } from "../rating/rating.component";

@Component({
  selector: 'app-carousel',
  imports: [CommonModule, SeacrflightComponent, WhychooseusComponent, ServiceComponent, RouterLink, RouterLinkActive, RatingComponent],
  templateUrl: './carousel.component.html',
  styleUrl: './carousel.component.css'
})
export class CarouselComponent {

image = "https://img.freepik.com/free-photo/online-shopping-concept_23-2151896821.jpg?t=st=1746610108~exp=1746613708~hmac=39a0cf6cb80717f6ca606eff9e988b4cba381ed7a39b7ef1bc0e31c358431927&w=1060";

cards = [
  {
    title: 'Product One',
    description: 'A great item with amazing features.',
    image: 'https://img.freepik.com/free-photo/online-shopping-concept_23-2151896821.jpg?t=st=1746610108~exp=1746613708~hmac=39a0cf6cb80717f6ca606eff9e988b4cba381ed7a39b7ef1bc0e31c358431927&w=1060',
    rating: 5
  },{
    title: 'Product One',
    description: 'A great item with amazing features.',
    image: 'https://img.freepik.com/free-photo/online-shopping-concept_23-2151896821.jpg?t=st=1746610108~exp=1746613708~hmac=39a0cf6cb80717f6ca606eff9e988b4cba381ed7a39b7ef1bc0e31c358431927&w=1060',
    rating: 5
  },{
    title: 'Product One',
    description: 'A great item with amazing features.',
    image: 'https://img.freepik.com/free-photo/online-shopping-concept_23-2151896821.jpg?t=st=1746610108~exp=1746613708~hmac=39a0cf6cb80717f6ca606eff9e988b4cba381ed7a39b7ef1bc0e31c358431927&w=1060',
    rating: 5
  },{
    title: 'Product One',
    description: 'A great item with amazing features.',
    image: 'https://img.freepik.com/free-photo/online-shopping-concept_23-2151896821.jpg?t=st=1746610108~exp=1746613708~hmac=39a0cf6cb80717f6ca606eff9e988b4cba381ed7a39b7ef1bc0e31c358431927&w=1060',
    rating: 5
  },{
    title: 'Product One',
    description: 'A great item with amazing features.',
    image: 'https://img.freepik.com/free-photo/online-shopping-concept_23-2151896821.jpg?t=st=1746610108~exp=1746613708~hmac=39a0cf6cb80717f6ca606eff9e988b4cba381ed7a39b7ef1bc0e31c358431927&w=1060',
    rating: 5
  },{
    title: 'Product One',
    description: 'A great item with amazing features.',
    image: 'https://img.freepik.com/free-photo/online-shopping-concept_23-2151896821.jpg?t=st=1746610108~exp=1746613708~hmac=39a0cf6cb80717f6ca606eff9e988b4cba381ed7a39b7ef1bc0e31c358431927&w=1060',
    rating: 5
  },{
    title: 'Product One',
    description: 'A great item with amazing features.',
    image: 'https://img.freepik.com/free-photo/online-shopping-concept_23-2151896821.jpg?t=st=1746610108~exp=1746613708~hmac=39a0cf6cb80717f6ca606eff9e988b4cba381ed7a39b7ef1bc0e31c358431927&w=1060',
    rating: 5
  },{
    title: 'Product One',
    description: 'A great item with amazing features.',
    image: 'https://img.freepik.com/free-photo/online-shopping-concept_23-2151896821.jpg?t=st=1746610108~exp=1746613708~hmac=39a0cf6cb80717f6ca606eff9e988b4cba381ed7a39b7ef1bc0e31c358431927&w=1060',
    rating: 5
  },{
    title: 'Product One',
    description: 'A great item with amazing features.',
    image: 'https://img.freepik.com/free-photo/online-shopping-concept_23-2151896821.jpg?t=st=1746610108~exp=1746613708~hmac=39a0cf6cb80717f6ca606eff9e988b4cba381ed7a39b7ef1bc0e31c358431927&w=1060',
    rating: 5
  },{
    title: 'Product One',
    description: 'A great item with amazing features.',
    image: 'https://img.freepik.com/free-photo/online-shopping-concept_23-2151896821.jpg?t=st=1746610108~exp=1746613708~hmac=39a0cf6cb80717f6ca606eff9e988b4cba381ed7a39b7ef1bc0e31c358431927&w=1060',
    rating: 5
  },{
    title: 'Product One',
    description: 'A great item with amazing features.',
    image: 'https://img.freepik.com/free-photo/online-shopping-concept_23-2151896821.jpg?t=st=1746610108~exp=1746613708~hmac=39a0cf6cb80717f6ca606eff9e988b4cba381ed7a39b7ef1bc0e31c358431927&w=1060',
    rating: 5
  },{
    title: 'Product One',
    description: 'A great item with amazing features.',
    image: 'https://img.freepik.com/free-photo/online-shopping-concept_23-2151896821.jpg?t=st=1746610108~exp=1746613708~hmac=39a0cf6cb80717f6ca606eff9e988b4cba381ed7a39b7ef1bc0e31c358431927&w=1060',
    rating: 5
  },{
    title: 'Product One',
    description: 'A great item with amazing features.',
    image: 'https://img.freepik.com/free-photo/online-shopping-concept_23-2151896821.jpg?t=st=1746610108~exp=1746613708~hmac=39a0cf6cb80717f6ca606eff9e988b4cba381ed7a39b7ef1bc0e31c358431927&w=1060',
    rating: 5
  },{
    title: 'Product One',
    description: 'A great item with amazing features.',
    image: 'https://img.freepik.com/free-photo/online-shopping-concept_23-2151896821.jpg?t=st=1746610108~exp=1746613708~hmac=39a0cf6cb80717f6ca606eff9e988b4cba381ed7a39b7ef1bc0e31c358431927&w=1060',
    rating: 5
  },{
    title: 'Product One',
    description: 'A great item with amazing features.',
    image: 'https://img.freepik.com/free-photo/online-shopping-concept_23-2151896821.jpg?t=st=1746610108~exp=1746613708~hmac=39a0cf6cb80717f6ca606eff9e988b4cba381ed7a39b7ef1bc0e31c358431927&w=1060',
    rating: 5
  },{
    title: 'Product One',
    description: 'A great item with amazing features.',
    image: 'https://img.freepik.com/free-photo/online-shopping-concept_23-2151896821.jpg?t=st=1746610108~exp=1746613708~hmac=39a0cf6cb80717f6ca606eff9e988b4cba381ed7a39b7ef1bc0e31c358431927&w=1060',
    rating: 5
  },{
    title: 'Product One',
    description: 'A great item with amazing features.',
    image: 'https://img.freepik.com/free-photo/online-shopping-concept_23-2151896821.jpg?t=st=1746610108~exp=1746613708~hmac=39a0cf6cb80717f6ca606eff9e988b4cba381ed7a39b7ef1bc0e31c358431927&w=1060',
    rating: 5
  },{
    title: 'Product One',
    description: 'A great item with amazing features.',
    image: 'https://img.freepik.com/free-photo/online-shopping-concept_23-2151896821.jpg?t=st=1746610108~exp=1746613708~hmac=39a0cf6cb80717f6ca606eff9e988b4cba381ed7a39b7ef1bc0e31c358431927&w=1060',
    rating: 5
  },{
    title: 'Product One',
    description: 'A great item with amazing features.',
    image: 'https://img.freepik.com/free-photo/online-shopping-concept_23-2151896821.jpg?t=st=1746610108~exp=1746613708~hmac=39a0cf6cb80717f6ca606eff9e988b4cba381ed7a39b7ef1bc0e31c358431927&w=1060',
    rating: 5
  },{
    title: 'Product One',
    description: 'A great item with amazing features.',
    image: 'https://img.freepik.com/free-photo/online-shopping-concept_23-2151896821.jpg?t=st=1746610108~exp=1746613708~hmac=39a0cf6cb80717f6ca606eff9e988b4cba381ed7a39b7ef1bc0e31c358431927&w=1060',
    rating: 5
  },

];

stars = Array(5).fill(0);
}
