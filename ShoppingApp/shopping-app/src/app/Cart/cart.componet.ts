import { Component, OnInit } from "@angular/core";
import { AppService } from "../app.service";
import { Cart } from "../ProductList/cart";
import { Router } from "@angular/router";

@Component({
    selector: 'cart',
    templateUrl: 'cart.component.html'
})
export class CartComponent implements OnInit {
    subtotalArray: any[] = [];
    unitPriceArray: any = [];
    totalCost;
    constructor(private _appservice: AppService ,private router : Router) {
        console.log("In Cart");
    }

    ngOnInit() {

        if (this._appservice.isLogin) {
            //hit db and get cart content
            
        }
        else {
            this._appservice.cartContent = JSON.parse(sessionStorage.getItem("productsInCart"));
            var i;
            for (i = 0; i < this._appservice.cartContent.length; i++) {

                this.unitPriceArray[i] = this._appservice.cartContent[i]["_unitPrice"];
            }
            this.totalCost = this.unitPriceArray.reduce((accumulator, currentValue) => accumulator + currentValue);

        }
    }

    updateSubTotal(i, unitprice, quantity) {
        this.subtotalArray[i] = unitprice * quantity;
        this.totalCost = this.subtotalArray.reduce((accumulator, currentValue) => accumulator + currentValue);
    }


    buy(){
        if(this._appservice.isLogin){
            // store  cart in database  and go to make payments
             
        }
        else{
            this._appservice.navigatedfromCart = true;
            this.router.navigate(['/login']);
        }
    }
}