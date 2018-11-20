 package service.represntation;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import order.OrderedItem;
import product.Product;

@XmlRootElement(name = "Order")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class OrderRepresentation extends AbstractRepresentation{
	
	private String orderNo;
	private String orderStatus;
	@XmlElement(name="productrepresentation")
	private ArrayList<ProductRepresentation> productsOnOrder;
	private String customerID;
	
	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public OrderRepresentation() {
		productsOnOrder = new ArrayList<>();
	}
	
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
	public ArrayList<ProductRepresentation> getProductsOnOrder() {
		return productsOnOrder;
	}
	public void setProductsOnOrder(ArrayList<ProductRepresentation> productsOnOrder) {
		this.productsOnOrder = productsOnOrder;
	}
	public void addProductRepresentation(ProductRepresentation productRepresentation) {
		productsOnOrder.add(productRepresentation);
	}

}
