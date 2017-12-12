import { OnInit, Input, Component } from "@angular/core";
import { AppService } from "../app.service";
import { PagerService } from "../_services/index";
import { ProductService } from "./product.service";
import { Router } from "@angular/router";
import { Cart } from "./cart";
import { CartService } from "../Cart/cart.service";

@Component({
  selector: 'product-data',
  templateUrl: 'product.component.html',
  styleUrls: ['./product.component.css']
})

export class ProductComponent implements OnInit {
  cart;
  // cartContent :any[] = [];
  pageSizes = [5, 25, 50, 100];
  totalItems: number;
  // pager object
  pager: any = {};
  pageSize: number = 10;
  // paged items
  pagedItems: any[];
  alreadyInCart = false;

  constructor(private router: Router, private _appService: AppService, private pagerService: PagerService, private _cartservice: CartService) {

  }

  ngOnInit(): void {
    this.setPage(1);
  }


  setPageSize(pageSize: number) {
    this.pageSize = pageSize;
    this.setPage(1);

  }


  setPage(page: number) {
    if (page < 1 || page > this.pager.totalPages) {
      return;
    }
    let from = (page - 1) * this.pageSize + 1;
    let to = page * this.pageSize;



    this._appService.getProductList("product", from, to, this._appService.sortByForProduct).subscribe(
      result => {
        this.totalItems = result[0];

        this.pagedItems = result[1];
        this.pager = this.pagerService.getPager(this.totalItems, page, this.pageSize);
      }
    );
  }

  addProductToCart(productId, productName, price, packages, loggedIn) {
    this._appService.productCountInCart += 1;

    /*check for duplicate product */
    console.log(1);
    
    this.checkInCart(productId, productName, price, packages)
    console.log(3);
    
    console.log(4);
    
    sessionStorage.setItem("productsInCart", JSON.stringify(this._appService.cartContent));
console.log(5);

    if (loggedIn == false) {

    }
    else {
      this._cartservice.addProductToCart(this._appService.cartContent, this._appService.customer.id).subscribe(
        result => {
          console.log(result);
        })
    }

  }

  /* Check if product is already in cart ; If so increase the quantity field of that product*/
  checkInCart(productId, productName, price, packages) {
    
    // for (var product of  this._appService.cartContent) {
      
    //   if (productId = product["_productId"]) {
    //     console.log(2);
    //     console.log(product);
        
    //     this.cart = new Cart(product["_productId"], productName, price, packages, product["_quantity"]+1);
    //     this.alreadyInCart = true;
    //     break
    //   }
      
      
    // }
    for(var i = 0;i<this._appService.cartContent.length;i++){
      if (productId = this._appService.cartContent[i]["_productId"]) {
        console.log(this._appService.cartContent[i]);
        
            this._appService.cartContent[i]["_quantity"] += 1;
            this.alreadyInCart = true;
            break
          }
          
    }
    /* If product is not already present in cart add it to cart */
    if (!this.alreadyInCart) {
      this.cart = new Cart(productId, productName, price, packages, 1);
      console.log(2);
      this._appService.cartContent.push(this.cart);
      
    }
  }

}