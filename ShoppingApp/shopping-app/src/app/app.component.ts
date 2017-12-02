import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { AppService } from './app.service';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map'
import * as _ from 'underscore';
import { ExamplePipe } from './app.examplepipe';
import { CustomerViewComponent } from './CustomerData/CustomerViewComponent';
import { Router } from '@angular/router';
import { ProductComponent } from './ProductList/product.component';
// import { FilterName} from './StringFilterPipe';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  @ViewChild(CustomerViewComponent) customerViewComponent;
  @ViewChild(ProductComponent) productComponent;

  isRefresh: boolean = false;
  // isenableRouterOutlet: boolean = false;
  isLoginOrSignUp: boolean = false;
  parentSortByForCustomer: string = "firstName";
  sortByListForCustomer = ['FirstName', 'LastName', 'Phone', 'City', 'Country'];

  id: number;
  customerId;
  ismouseenter = false;
  parentSortByForProduct: string = "ProductName";
  sortByListForProduct = ["ProductName", "UnitPrice"];


  constructor(private router: Router, private _appservice: AppService) {
  }

  ngOnInit() {
    
    this.router.navigate([''])
    this.isRefresh = false;
    if(sessionStorage.getItem("username") == null){
      if(isNaN(parseInt(sessionStorage.getItem("noOfProductsInCart")))){

      }
      else{
        this._appservice.productCountInCart = parseInt(sessionStorage.getItem("noOfProductsInCart"));
        this._appservice.cartContent =JSON.parse(sessionStorage.getItem("productsInCart"));
      }
    }
    else{
      this._appservice.numberofProductsInCart(this._appservice.customer.id).subscribe(
        response => {
          console.log(""+response);
          
          this._appservice.productCountInCart = response;
        }
      )
    }
  }
  enableRouterOutlet() {
    this._appservice.isenableRouterOutlet = true;
  }
  LoginorSignUp() {
    document.getElementById("login").innerHTML = "";
    
    this.isLoginOrSignUp = true;
    this._appservice.isProductsDisplay = false;
    this.router.navigate(['/login'])
  }

  // From customer component
  navigateToCustomerDetails() {
    console.log("DETAILS");
    
    this.ismouseenter = false;
    this._appservice.isProductsDisplay = false;
    this._appservice.isCustomerDetails = true;
    this.router.navigate(['/customerdetails']);
  }
  navigateToOrderDetails(id) {
    console.log("ORDER");
    
    this.ismouseenter = false;
    this._appservice.isProductsDisplay = false;
    this._appservice.isCustomerOrderDetails = true;
    this.router.navigate(['/orderdetails', id]);
  }

  logout() {
    document.getElementById("login").innerHTML = "";
    this._appservice.showCustomerDropdown = true;
    sessionStorage.clear();
    this._appservice.isProductsDisplay = false;

    this.router.navigate(['/login']);
  }

  onmouseover() {
    this.ismouseenter = true;
  }

  onmouseleave() {
    this.ismouseenter = false;
  }

  updateSortBy(value) {
    this._appservice.sortByForProduct = value;
  }
  getBackProductList() {
    this._appservice.isProductsDisplay = true;
  }

  goToCart(){
    
    this._appservice.isProductsDisplay = false;
    this.router.navigate(['/cart']);
  }
  

}
