import { Component, OnInit } from "@angular/core";
import { AppService } from "../app.service";
import { ActivatedRoute, Router } from "@angular/router";
import { CustomerService } from "./customer.service";

@Component({
    templateUrl: 'orderitem.component.html',
    providers: [CustomerService]
})
export class OrderItemComponent implements OnInit {

    orderid: number;
    productid: number;
    constructor(private router :Router ,private _appservice: AppService, private _customerservice: CustomerService, private route: ActivatedRoute) {
        console.log("orderitems constructor");
    }

    ngOnInit() {

        this.route.params.subscribe(params => {
            this.orderid = +params['id']
        })

        this._customerservice.getOrderItems(this.orderid).subscribe(
            result => {
                // console.log(result);
                this._customerservice.orderitems = result;

            }
        );
    }

    getProductDescription(productId) {

        this._appservice.getproductDescription(productId).subscribe(
            result => {
                console.log(result)
                this._customerservice.isproductDescription = true;

                this._customerservice.product = result;
            }
        )
    }
    goBack() {
        this.router.navigate(['/orderdetails',this._appservice.customer.id]);
    }
}