package beans;

import java.io.Serializable;

public class Cart implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer _productId;
	private Integer _customerId;
	private String _productName;
	private float _unitPrice;
	private String _packages;

	private Integer _quantity;

	public Integer get_productId() {
		return _productId;
	}

	public void set_productId(Integer _productId) {
		this._productId = _productId;
	}

	public Integer get_customerId() {
		return _customerId;
	}

	public void set_customerId(Integer _customerId) {
		this._customerId = _customerId;
	}

	public String get_productName() {
		return _productName;
	}

	public void set_productName(String _productName) {
		this._productName = _productName;
	}

	public float get_unitPrice() {
		return _unitPrice;
	}

	public void set_unitPrice(float _unitPrice) {
		this._unitPrice = _unitPrice;
	}

	public String get_packages() {
		return _packages;
	}

	public void set_packages(String _packages) {
		this._packages = _packages;
	}

	public Integer get_quantity() {
		return _quantity;
	}

	public void set_quantity(Integer _quantity) {
		this._quantity = _quantity;
	}

}
