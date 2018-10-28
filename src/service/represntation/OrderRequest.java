package service.represntation;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import order.OrderedItem;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class OrderRequest {
	
	private ArrayList <OrderedItem> items;
	private String customerId;
	private String ccNo;
	
	public ArrayList<OrderedItem> getItems() {
		return items;
	}

	public void setItems(ArrayList<OrderedItem> items) {
		this.items = items;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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
}
