package service.represntation;

public class linkMetaData {
	private String attribute;
	private String attvalue;
	
	public linkMetaData() {}
	
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public String getValue() {
		return attvalue;
	}
	public void setValue(String value) {
		this.attvalue = value;
	}
	
	@Override
	public String toString() {
		return "<link " + this.attribute + "=" + "\"" + this.attvalue + "\"" + "/>";
	}
	
}
