import { Injectable } from "@angular/core";
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class CartService{

    constructor(private _http: Http) { }

    addProductToCart(cart){
        const url = 'http://localhost:8180/ShoppingApp/api/products/addProductToCart/';
        console.log(cart);
        return this._http.post(url,{cart}).map(res =>res.json());
    }

    getCartContent(customerId:number){
        const url = 'http://localhost:8180/ShoppingApp/api/products/getCartContent/';
        return this._http.post(url,{customerId}).map(res => res.json());
    }
}