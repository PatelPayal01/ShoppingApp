import { Injectable } from "@angular/core";
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class CartService {

    constructor(private _http: Http) { }

    addProductToCart(cart,customerId) {
        console.log(cart);
        const url = 'http://localhost:8180/ShoppingApp/api/products/addProductToCart/';
        
        return this._http.post(url, { cart ,customerId}).map(res => res.json());
    }

    getCartContent(_customerId: number) {
        console.log("Cart service");
        
        const url = 'http://localhost:8180/ShoppingApp/api/products/getCartContent/';
        return this._http.post(url, { _customerId }).map(res => res.json());
    }
}