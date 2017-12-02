import { Component, OnInit } from "@angular/core";
import { AppService } from "../app.service";
import { Router } from "@angular/router";

@Component({
    selector: 'cust-details',
    templateUrl: 'customerdetails.component.html'
})

export class CustomerDetailsComponent implements OnInit {
    constructor(private _appservice: AppService, private router: Router) {
        this._appservice.isCustomerDetails = true;
    }

    ngOnInit() {
        // console.log("CustomerDetails ngOnIt");
    }
    gotoCustomer() {
        this._appservice.isCustomerDetails = false;
        this._appservice.isCustomerOrderDetails = false;
        this.router.navigate(['/customer'])
    }

}