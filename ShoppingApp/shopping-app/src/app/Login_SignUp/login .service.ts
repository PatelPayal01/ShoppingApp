import { Injectable } from "@angular/core";
import { Http, Response, Headers, RequestOptions } from '@angular/http';
// import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import { SignUpData } from "./SignUpData";

@Injectable()
export class LoginService {

    constructor(private _http: Http) { }

    signUp(data: SignUpData) {
        const url = 'http://localhost:8180/ShoppingApp/api/loginNsignup/signup/';
        // let headers = new Headers({ 'Content-Type': 'application/JSON'});
        // let options = new RequestOptions({ headers: headers });
        return this._http.post(url, data);
    }

    login(username: string, password: string) {
        console.log("inLoginservice");
        const url = 'http://localhost:8180/ShoppingApp/api/hello/login/';
        return this._http.post(url, { username, password }).map(res => res.json());
    }
}