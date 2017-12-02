import { Injectable } from "@angular/core";
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';


@Injectable()
export class ProductService {

    constructor(private _http: Http) { }

    // addProductToCart(productId: number, customerId: number) {
    //     const url = 'http://localhost:8180/ShoppingApp/api/products/addProductToCart/';
    //     return this._http.post(url, { productId, customerId }).map(res => res.json());
    // }


}