 package service.represntation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Product")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class ProductRepresentation extends AbstractRepresentation{
	
	private String id;
	private String name;
	private String description;
	private double price;
	private String productOwnerID;
	private PartnerRepresentation partnerRep;
	private String quantityOnOrder;
	
	public String getQuantityOnOrder() {
		return quantityOnOrder;
	}
	public void setQuantityOnOrder(String quantityOnOrder) {
		this.quantityOnOrder = quantityOnOrder;
	}
	public String getId() {
		return id; 
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getProductOwnerID() {
		return productOwnerID;
	}
	public void setProductOwnerID(String productOwnerID) {
		this.productOwnerID = productOwnerID;
	}
	public PartnerRepresentation getPartnerRep() {
		return partnerRep;
	}
	public void setPartnerRep(PartnerRepresentation partnerRep) {
		this.partnerRep = partnerRep;
	}


}
