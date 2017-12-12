import { Component, OnInit } from "@angular/core";
import { AppService } from "../app.service";
import { Cart } from "../ProductList/cart";
import { Router } from "@angular/router";
import { CartService } from "./cart.service";
import { log } from "util";

@Component({
    selector: 'cart',
    templateUrl: 'cart.component.html'
})
export class CartComponent implements OnInit {
    subtotalArray: any[] = [];
    unitPriceArray: any = [];
    totalCost;
    constructor(private _appservice: AppService, private router: Router, private _cartservice: CartService) {
        console.log("In Cart");
        

    }

    ngOnInit() {

        var i;
        for (i = 0; i < this._appservice.cartContent.length; i++) {

            this.unitPriceArray[i] = this._appservice.cartContent[i]["_unitPrice"];
        }


        this.totalCost = this.unitPriceArray.reduce((a, b) => a + b);
        console.log(this.totalCost);

        if (this._appservice.isLogin) {
            //hit db and get cart content and also a add session content to cartContent

        }
        else {

        }
    }

    updateSubTotal(i, unitprice, quantity) {
        this.subtotalArray[i] = unitprice * quantity;
        this.totalCost = this.subtotalArray.reduce((accumulator, currentValue) => accumulator + currentValue);
    }


    buy() {
        if (this._appservice.isLogin) {
            // store  cart in database  and go to make payments

        }
        else {
            this._appservice.navigatedfromCart = true;
            this.router.navigate(['/login']);
        }
    }

}