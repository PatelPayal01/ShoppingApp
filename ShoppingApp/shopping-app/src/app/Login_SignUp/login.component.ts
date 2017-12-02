import { Component, OnInit, DoCheck } from '@angular/core';
import { AppService } from '../app.service';
import { Router } from '@angular/router';
import { SignUpData } from './SignUpData';
import { LoginService } from './login .service';
import { SessionStorageService } from "ng2-webstorage";
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

    constructor(private _appservice: AppService, private _loginservice: LoginService, private router: Router) {
        if (sessionStorage.getItem("username")) {
            this._appservice.showCustomerDropdown = true;
            this._appservice.isLogin = true;
            this._appservice.isProductsDisplay = true;


            this._appservice.customer = JSON.parse(sessionStorage.getItem("customerdetails"));
            var value = "Welcome " + this._appservice.customer.firstName;
            document.getElementById("login").innerHTML = value;

            this.router.navigate(['/customer']);

        }

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
        var value = "Welcome " + this._appservice.customer.firstName;

        document.getElementById("login").innerHTML = value;
        this._appservice.showCustomerDropdown = true;
        // this._appservice.isProductsDisplay = true;

        if (this._appservice.navigatedfromCart) {
            this._appservice.isProductsDisplay = false;
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