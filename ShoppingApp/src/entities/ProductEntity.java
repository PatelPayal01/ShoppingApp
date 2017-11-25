package entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Product")
public class ProductEntity {

	@Id
	@GenericGenerator(name = "kaugen", strategy = "increment")
	@GeneratedValue(generator = "kaugen")
	private Integer id;
	private String productName;
	private Float unitPrice;
	private char isDiscontinued;
	@Column(name = "package")
	private String packages;

	
	
	public String getPackages() {
		return packages;
	}

	public void setPackages(String packages) {
		this.packages = packages;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "supplierid")
	private SupplierEntity supplierEntity;

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

	

	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}


	public char getIsDiscontinued() {
		return isDiscontinued;
	}

	public void setIsDiscontinued(char isDiscontinued) {
		this.isDiscontinued = isDiscontinued;
	}
	
	public SupplierEntity getSupplierEntity() {
		return supplierEntity;
	}

	public void setSupplierEntity(SupplierEntity supplierEntity) {
		this.supplierEntity = supplierEntity;
	}


}
