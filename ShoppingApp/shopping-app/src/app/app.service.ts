import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import { Cart } from './ProductList/cart';

@Injectable()
export class AppService {

    public totalItems = 0;
    public pagedItems:any[];
    public navigatedfromCart = false;
    public cartContent: Cart[] = [];
    public productCountInCart = 0;
    public showCustomerDropdown = false;
    public sortByForProduct = "productname";
    public customer;
    public isLogin = false;
    public ordersOfCustomer;
    public isCustomerDetails = false;
    public isCustomerOrderDetails = false;
    public isProductsDisplay = true;
    public isenableRouterOutlet = false;

    constructor(private _http: Http) {
        console.log(this.cartContent);

    }
    rowCount() {
        const url = 'http://localhost:8180/ShoppingApp/api/hello/rowCount/';
        let headers = new Headers();
        headers.append('Content-Type', 'application/JSON');
        return this._http.get(url)
            .map(res => res.json())

    }

    getCustomerList(custOrProd: string, startIndex: number, endIndex: number, sortByForCustomer: string) {
        const url = 'http://localhost:8180/ShoppingApp/api/hello/paginateData/';
        
        return this._http.post(url, { custOrProd, startIndex, endIndex, sortByForCustomer }).map(res => res.json());
    }
    
    getProductList(custOrProd: string, startIndex: number, endIndex: number, sortByForProduct: string,searchText:string) {
        console.log("pag service");
        const url = 'http://localhost:8180/ShoppingApp/api/hello/paginateData/';

        return this._http.post(url, { custOrProd, startIndex, endIndex, sortByForProduct,searchText }).map(res => res.json());
    }


    getOrdersofCustomer(customerId: number) {

        const url = 'http://localhost:8180/ShoppingApp/api/hello/ordersOfCustomer/'
        return this._http.post(url, { customerId }).map(res => res.json());
    }


    getproductDescription(productId: number) {
        const url = 'http://localhost:8180/ShoppingApp/api/customer/productDescription/';

        return this._http.post(url, { productId }).map(res => res.json());
    }

    searchProduct(searchProduct){
        const url = 'http://localhost:8180/ShoppingApp/api/products/searchProduct/';
        console.log(searchProduct);
        
                return this._http.post(url, searchProduct).map(res => res.json()); 

    }



}
