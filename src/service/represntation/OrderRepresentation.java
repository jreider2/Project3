 package service.represntation;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import product.Product;

@XmlRootElement(name = "Order")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class OrderRepresentation {
	private String orderNo;
	private String orderStatus;
	private ArrayList<Product> productsOnOrder;
	
	public OrderRepresentation() {}
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public ArrayList<Product> getProductsOnOrder() {
		return productsOnOrder;
	}
	public void setProductsOnOrder(ArrayList<Product> productsOnOrder) {
		this.productsOnOrder = productsOnOrder;
	}
	

}
