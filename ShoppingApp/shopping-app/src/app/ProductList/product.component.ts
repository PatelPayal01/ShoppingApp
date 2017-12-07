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
  cartIndex = -1;
  pageSizes = [5, 25, 50, 100];
  totalItems: number;
  // pager object
  pager: any = {};
  pageSize: number = 10;
  // paged items
  pagedItems: any[];

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
    this.cartIndex += 1;
    if (loggedIn == false) {
      this.cart = new Cart(productId, productName, price, packages, 1);
      this._appService.cartContent.push(this.cart);
      sessionStorage.setItem("productsInCart", JSON.stringify(this._appService.cartContent));
      sessionStorage.setItem("noOfProductsInCart", "" + this._appService.productCountInCart)
    }
    else {
      this.cart = new Cart(productId, productName, price, packages, 1);
      this._appService.cartContent.push(this.cart);
      this._cartservice.addProductToCart(this._appService.cartContent,this._appService.customer.id).subscribe(
        result => {
          console.log(result);
        })
    }

  }

}