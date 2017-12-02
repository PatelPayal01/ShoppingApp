import { Component, OnInit, ViewChild } from "@angular/core";
import { AppService } from "../app.service";
import { RouterModule, Router, ActivatedRoute } from "@angular/router";
import { ProductComponent } from "../ProductList/product.component";
@Component({
    selector: 'customer',
    templateUrl: 'customer.component.html',
    styleUrls : ['customer.component.css']
})

export class CustomerComponent implements OnInit {

    parentSortByForProduct: string = "ProductName";
    sortByListForProduct = ["ProductName", "UnitPrice"];

@ViewChild(ProductComponent) productComponent;
    constructor(private _appservice: AppService, private router: Router) {
    }
    ngOnInit() {
    }

}