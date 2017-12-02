import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';

@Injectable()
 export class CustomerService{
     orderitems;
     isproductDescription = false;
     product;
    constructor(private _http: Http) { }

    getOrderItems(orderId : number){
        const url = 'http://localhost:8180/ShoppingApp/api/customer/orderitems/';

        return this._http.post(url , {orderId}).map(res =>res.json());
    }

    // getproductDescription(productId : number){
    //     const url = 'http://localhost:8180/ShoppingApp/api/customer/productDescription/';

    //     return this._http.post(url, {productId}).map(res => res.json());
    // }
 }