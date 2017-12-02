import { Component, OnInit } from "@angular/core";
import { AppService } from "../app.service";
import { ActivatedRoute, Router } from "@angular/router";

@Component({
    selector: 'orderdetails',
    templateUrl: 'order.component.html',
    styleUrls: ['order.component.css']
})
export class OrderDetailsComponent implements OnInit {
    id;
    message : string;
    customerId: number;
    constructor(private _appservice: AppService, private route: ActivatedRoute, private router: Router) {
        this._appservice.isCustomerOrderDetails = true;
        console.log("OrderDetails Constructor");
    }
    ngOnInit() {

        console.log("OrderDetails noOnInit")
        this.route.params.subscribe(params => {
            this.customerId = +params['id']; // (+) converts string 'id' to a number


            // In a real app: dispatch action to load the details here.
        });

        this._appservice.getOrdersofCustomer(this.customerId).subscribe(
            result => {
                console.log(result)
                this._appservice.ordersOfCustomer = result;
                this.message = this._appservice.ordersOfCustomer.message;
            }
        );
    }



    navigate(id:number){
        this.router.navigate(['/orderitems',id])
    }
    gotoCustomer() {
        this._appservice.isCustomerDetails = false;
        this._appservice.isCustomerOrderDetails = false;
        this.router.navigate(['/customer'])
    }

}