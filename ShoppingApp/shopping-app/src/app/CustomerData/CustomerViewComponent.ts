import { OnInit, Input, Component } from "@angular/core";
import { AppService } from "../app.service";
import { PagerService } from "../_services/index";

@Component({
  selector: 'customer-data',
  templateUrl: 'CustomerTable.html',
  // styleUrls: ['./app.component.css']
})

export class CustomerViewComponent implements OnInit {
  
  totalItems: number;
  // pager object
  pager: any = {};
  pageSize: number = 10;
  // paged items
  pagedItems: any[];
  @Input() sortBy: string;
  constructor(private _appService: AppService, private pagerService: PagerService) { }

  ngOnInit(): void {
    this.setPage(1);
  }

  
  setPageSize(pageSize: number) {
    this.pageSize = pageSize;
    this.setPage(1);
  }
  setPage(page: number) {
    if (page < 1 || page > this.pager.totalPages) {
      return;
    }
    // get pager object from service

    // get current page of items//Make service in java to get this data 

    // this.pagedItems = this.customer.slice(this.pager.startIndex, this.pager.endIndex + 1);
    let from = (page - 1) * this.pageSize + 1;
    let to = page * this.pageSize;
    // console.log(this.sortBy+"sort by")

    this._appService.getCustomerList("customer",from, to,this.sortBy).subscribe(
      result => {
        this.totalItems = result[0];
        this.pagedItems = result[1];
        this.pager = this.pagerService.getPager(this.totalItems, page, this.pageSize);
      }
    );

  }
}

