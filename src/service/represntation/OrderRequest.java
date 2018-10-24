package service.represntation;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import partner.Partner;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class OrderRequest {
	
	private String id;
	private ArrayList <ProductRequest> items;
	private PartnerRepresentation partnerRep;
	private String customerId;
	private String orderStatus;
	private String ccNo;
	
	public ArrayList<ProductRequest> getItems() {
		return items;
	}

	public void setItems(ArrayList<ProductRequest> items) {
		this.items = items;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getCcNo() {
		return ccNo;
	}

	public void setCcNo(String ccNo) {
		this.ccNo = ccNo;
	}

	public OrderRequest() {
		items = new ArrayList<>();
	}

	public String getId() {
		return this.id;
	}

	public ArrayList<ProductRequest> getProducts() {
		return this.items;
	}

	public void setProducts(ArrayList<ProductRequest> items) {
		this.items = items;
	}

	public PartnerRepresentation getPartnerRep() {
		return this.partnerRep;
	}

	public void setPartnerRep(PartnerRepresentation partnerRep) {
		this.partnerRep = partnerRep;
	}

	public void setId(String id) {
		this.id = id;
	}

}
