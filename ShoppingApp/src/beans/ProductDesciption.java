package beans;

public class ProductDesciption {
	private Integer id;
	private String productName;
	private float unitPrice;
	private String packageAvailable;
	private char isDiscontinued;
	private Integer supplierId;
	
	public ProductDesciption(Integer id,String productName,Integer supplierId ,float unitPrice,String packageAvailable){
		this.id = id;
		this.packageAvailable = packageAvailable;
		this.productName = productName;
		this.supplierId = supplierId;
		this.unitPrice = unitPrice;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public float getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getPackageAvailable() {
		return packageAvailable;
	}
	public void setPackageAvailable(String packageAvailable) {
		this.packageAvailable = packageAvailable;
	}
	public char getIsDiscontinued() {
		return isDiscontinued;
	}
	public void setIsDiscontinued(char isDiscontinued) {
		this.isDiscontinued = isDiscontinued;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	
	
}
