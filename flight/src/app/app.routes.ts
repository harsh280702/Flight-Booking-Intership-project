

import { Component } from '@angular/core';
import { Routes } from '@angular/router';
import { ServiceComponent } from './service/service.component';
import { CarouselComponent } from './carousel/carousel.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { ContactComponent } from './contact/contact.component';
import { FlightComponent } from './flight/flight.component';
import { LoginComponent } from './login/login.component';

import { SingupComponent } from './singup/singup.component';
import { PurchaseComponent } from './purchase/purchase.component';



export const routes: Routes = [

  {
    path:'',
  component:CarouselComponent  }
  ,
  {
    path:'services',
    component:ServiceComponent
  }
  ,
  {
    path:'about',
    component:AboutUsComponent
  },
  {
    path:'contact',
    component:ContactComponent
  },
  {
    path:'FGH',
    component:FlightComponent
  }

  ,{
    path:"login",
    component:LoginComponent
  }
  ,
{
  path:"PDT",
  component:PurchaseComponent
},


  {
    path:"SingUp",
    component:SingupComponent
  }
  ,

  { path: '', redirectTo: 'signup', pathMatch: 'full' }

];
