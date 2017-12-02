import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppService } from './app.service';
import { HttpModule } from '@angular/http';

import { ExamplePipe } from './app.examplepipe';
import { CustomerViewComponent } from './CustomerData/CustomerViewComponent'
import { PagerService } from './_services/index';

import { AppComponent } from './app.component';
import { LoginComponent } from './Login_SignUp/login.component'
import { AppRoutingModule, routingComponents } from './app-routing.module'
import { CustomerComponent } from "./Customer/customer.component";
import { CustomerDetailsComponent } from "./Customer/customerdetails.component";
import { OrderDetailsComponent } from './Customer/order.component';
import { LoginService } from './Login_SignUp/login .service';
import { Ng2Webstorage } from 'ng2-webstorage';
import { OrderItemComponent } from './Customer/orderitem.component';
import { CustomerService } from './Customer/customer.service';
import { ProductComponent } from './ProductList/product.component';
import { ProductService } from './ProductList/product.service';
import { CartComponent } from './Cart/cart.componet';
import { CartService } from './Cart/cart.service';


@NgModule({
  declarations: [AppComponent,
    ExamplePipe,
    CustomerViewComponent,
    routingComponents,
    CustomerComponent,
    CustomerDetailsComponent,
    OrderItemComponent,
    OrderDetailsComponent,
    ProductComponent, CartComponent],

  imports: [BrowserModule,
    HttpModule,
    FormsModule,
    AppRoutingModule],

  providers: [AppService,
              PagerService,
              LoginService,
              CustomerService,
              ProductService,
              CartService],

  bootstrap: [AppComponent]
})
export class AppModule { }
