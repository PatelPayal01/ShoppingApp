import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './Login_SignUp/login.component';
import { CustomerComponent } from './Customer/customer.component';
import { CustomerDetailsComponent } from './Customer/customerdetails.component';
import { OrderDetailsComponent } from './Customer/order.component';
import { AppService } from './app.service';
import { OrderItemComponent } from './Customer/orderitem.component';
import { AppComponent } from './app.component';
import { CartComponent } from './Cart/cart.componet';


const routes: Routes = [
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: 'customer',
        component: CustomerComponent

    },
    {
        path: 'customerdetails',
        component: CustomerDetailsComponent

    },
    {

        path: 'orderdetails/:id',
        component: OrderDetailsComponent,


    },
    {
        path: 'orderitems/:id',
        component: OrderItemComponent
    },
    {
        path : 'cart',
        component:CartComponent
    }


];

@NgModule({
    imports: [
        RouterModule.forRoot(routes)
    ],
    exports: [RouterModule]
})

export class AppRoutingModule {

}
export const routingComponents = [LoginComponent, CustomerComponent, OrderDetailsComponent, CustomerDetailsComponent]