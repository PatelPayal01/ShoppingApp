export class Cart {
    private _productId: number;
    private _productName: string;
    private _unitPrice: number;
    private _packages: string;
    // private _customerId: number;
    private _quantity: number;

    constructor(_productId, _productName, _unitPrice, _packages, _quantity) {
        this._productId = _productId;
        this._productName = _productName;
        this._unitPrice = _unitPrice;
        this._packages = _packages;
        // this._customerId = _customerId;
        this._quantity = _quantity;
    }

    public get productId(): number {
        return this._productId
    }

    // public get customerId(): number {
    //     return this._customerId
    // }

    public get productName(): string {
        return this._productName
    }

    public get unitPrice(): number {
        return this._unitPrice
    }

    public get packages(): string {
        return this._packages
    }

    public get quantity(): number {
        return this._quantity
    }

    // public set customerId(value: number) {
    //     this._customerId = value;
    // }

    public set productId(value: number) {
        this._productId = value;
    }
    public set productName(value: string) {
        this._productName = value;
    }
    public set unitPrice(value: number) {
        this._unitPrice = value;
    }
    public set packages(value: string) {
        this._packages = value;
    }

    public set quantity(value: number) {
        this._quantity = value;
    }
}

