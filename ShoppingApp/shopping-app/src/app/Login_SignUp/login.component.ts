import { Component, OnInit, DoCheck } from '@angular/core';
import { AppService } from '../app.service';
import { Router } from '@angular/router';
import { SignUpData } from './SignUpData';
import { LoginService } from './login .service';
import { SessionStorageService } from "ng2-webstorage";
import { CartService } from '../Cart/cart.service';
@Component({
    selector: 'login',
    templateUrl: './login.component.html',
    styleUrls: ['login.component.css']
})
export class LoginComponent {
    isMatching: boolean;
    customer;
    ccpassword;
    username: string;
    password: string;
    signupData = new SignUpData();
    isUnsuccessfullLogin: boolean = false;

    constructor(private _appservice: AppService, private _cartservice: CartService, private _loginservice: LoginService, private router: Router) {
    }




    login() {
        this._loginservice.login(this.username, this.password)
            .subscribe(
            result => {
                this._appservice.customer = result;

                if (this._appservice.customer.message == null) {
                    this._appservice.isLogin = true;

                    sessionStorage.setItem("username", this.username)
                    sessionStorage.setItem("customerdetails", JSON.stringify(result));

                    this._appservice.isProductsDisplay = true;
                    this.loginSuccessfull();
                }
                else {
                    this.isUnsuccessfullLogin = true;
                }
            }
            )
    }


    loginSuccessfull() {

        this._appservice.showCustomerDropdown = true;
        this._cartservice.getCartContent(this._appservice.customer.id).subscribe(
            result => {
                if (result != null) {

                    this._appservice.cartContent = result;
                    sessionStorage.setItem("productsInCart", JSON.stringify(this._appservice.cartContent));
                    this._appservice.productCountInCart = result.length;
                }
            }
        )
        if (this._appservice.navigatedfromCart) {
            this._appservice.isProductsDisplay = false;
            /*Add products in session to database */
            this._cartservice.addProductToCart(JSON.parse(sessionStorage.getItem("productsInCart")), this._appservice.customer.id).subscribe(
                result => {
                    console.log(result);
                    this._appservice.cartContent= JSON.parse(sessionStorage.getItem("productsInCart"));

                }
            )
            this.router.navigate(['/cart']);
        }
        else {
            this.router.navigate(['/customer']);
        }
    }


    signUp(value) {

        this._loginservice.signUp(this.signupData).subscribe(
            result => {
                console.log(result)
            }
        );
    }


    matchPassword(event1) {
        console.log(event1);

        if (this.signupData.password == event1) {
            this.isMatching = true;
        }
        else {
            this.isMatching = false;
        }
    }
}