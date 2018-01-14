import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { AppService } from './app.service';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import 'rxjs/add/operator/map'
import { CustomerViewComponent } from './CustomerData/CustomerViewComponent';
import { Router } from '@angular/router';
import { ProductComponent } from './ProductList/product.component';
import { CartService } from './Cart/cart.service';
import { Paginatedata } from './Paginatedata';
import { SearchProduct } from './SearchProduct';
import { PagerService } from './_services/pager.service';
import { log } from 'util';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  @ViewChild(CustomerViewComponent) customerViewComponent;
  @ViewChild(ProductComponent) productComponent: ProductComponent;

  pager: any = {};

  searchText = "";

  isRefresh: boolean = false;
  isLoginOrSignUp: boolean = false;
  parentSortByForCustomer: string = "firstName";
  sortByListForCustomer = ['FirstName', 'LastName', 'Phone', 'City', 'Country'];

  id: number;
  customerId;
  ismouseenter = false;
  parentSortByForProduct: string = "ProductName";
  sortByListForProduct = ["ProductName", "UnitPrice"];


  constructor(private pagerService:PagerService,private router: Router, private _appservice: AppService, private _cartservice: CartService) {
  }
  pageSize: number = 10;
  page;
  setPage(page: number) {
    if (page < 1 || page > this.pager.totalPages) {
      return;
    }
    let from = (page - 1) * this.pageSize + 1;
    let to = page * this.pageSize;
  }
  
  ngOnInit() {

    this.router.navigate([''])
    this.isRefresh = false;

    if (sessionStorage.getItem("username") == null) {
      if (sessionStorage.getItem("productsInCart")) {
        this._appservice.cartContent = JSON.parse(sessionStorage.getItem("productsInCart"));
        this._appservice.productCountInCart = this._appservice.cartContent.length;

      }
    }
    else {
      this._appservice.showCustomerDropdown = true;
      this._appservice.isLogin = true;
      this._appservice.isProductsDisplay = true;


      this._appservice.customer = JSON.parse(sessionStorage.getItem('customerdetails'));
      this._appservice.isLogin = true;
      console.log(this._appservice.cartContent);

      this._cartservice.getCartContent(this._appservice.customer.id).subscribe(
        result => {
          this._appservice.cartContent = result;
          if (result != null)
            this._appservice.productCountInCart = this._appservice.cartContent.length;
        }
      )

      this.router.navigate(['/customer']);
    }
  }
  enableRouterOutlet() {
    this._appservice.isenableRouterOutlet = true;
  }
  LoginorSignUp() {

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

  goToCart() {

    this._appservice.isProductsDisplay = false;
    this._appservice.cartContent = JSON.parse(sessionStorage.getItem("productsInCart"));
    console.log(this._appservice.cartContent);

    this.router.navigate(['/cart']);
  }
  
  
  searchProduct() {
    // var pagedData: Paginatedata = new Paginatedata(1, 2, "productName");
    // var data =new SearchProduct(this.searchText,pagedData)
    // console.log(this.searchText);
    // this._appservice.searchProduct(data).subscribe(
    //   result => {
    //     console.log(result);
    //     this.productComponent.pagedItems = result;

    //   }
    // )
    console.log(this.searchText);
    
    this._appservice.getProductList("product", 0,10,"productname",this.searchText).subscribe(
      result => {
        console.log(this.searchText);
        
        this._appservice.totalItems = result[0];

        this.productComponent.pagedItems = result[1];
        console.log(result[1]);
        
        this.pager = this.pagerService.getPager(this._appservice.totalItems, 1, this.pageSize);
      }
    )




  }

}
