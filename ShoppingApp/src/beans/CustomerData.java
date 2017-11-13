package beans;

public class CustomerData {
	private String custOrProd;
	private Integer startIndex;
	private Integer endIndex;
	private String sortByForCustomer;
	private String sortByForProduct;

	public CustomerData() {
	}

	public String getSortByForCustomer() {
		return sortByForCustomer;
	}

	public void setSortByForCustomer(String sortByForCustomer) {
		this.sortByForCustomer = sortByForCustomer;
	}

	public String getSortByForProduct() {
		return sortByForProduct;
	}

	public void setSortByForProduct(String sortByForProduct) {
		this.sortByForProduct = sortByForProduct;
	}

	public String getCustOrProd() {
		return custOrProd;
	}

	public void setCustOrProd(String custOrProd) {
		this.custOrProd = custOrProd;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}
}
