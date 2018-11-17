package service.represntation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "CreditCard")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class CreditCardRepresentation extends AbstractRepresentation{
	private String ccNo;
	private String nameOncc;
	public String getCcNo() {
		return ccNo;
	}
	public void setCcNo(String ccNo) {
		this.ccNo = ccNo;
	}
	public String getNameOncc() {
		return nameOncc;
	}
	public void setNameOncc(String nameOncc) {
		this.nameOncc = nameOncc;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	private String expDate;
	
	
}
