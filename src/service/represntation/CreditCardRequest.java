package service.represntation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class CreditCardRequest {
	private String ccNum;
	private String ccHolder;
	private String ccExpirationDate;
	private String ccSecurityCode;
	private String ccCustomerNo;


	public CreditCardRequest() {}
	
	public String getCcNum() {
		return ccNum;
	}
	public void setCcNum(String ccNum) {
		this.ccNum = ccNum;
	}
	public String getCcHolder() {
		return ccHolder;
	}
	public void setCcHolder(String ccHolder) {
		this.ccHolder = ccHolder;
	}
	public String getCcExpirationDate() {
		return ccExpirationDate;
	}
	public void setCcExpirationDate(String ccExpirationDate) {
		this.ccExpirationDate = ccExpirationDate;
	}
	public String getCcSecurityCode() {
		return ccSecurityCode;
	}
	public void setCcSecurityCode(String ccSecurityCode) {
		this.ccSecurityCode = ccSecurityCode;
	}	
	public String getCcCustomerNo() {
		return ccCustomerNo;
	}

	public void setCcCustomerNo(String ccCustomerNo) {
		this.ccCustomerNo = ccCustomerNo;
	}
	
	
}
