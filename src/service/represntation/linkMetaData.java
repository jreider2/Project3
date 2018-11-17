package service.represntation;

public class linkMetaData {
	private String attribute;
	private String attvalue;
	private String mediaType;
	
	//Constructor initializes the link data. This makes it easier to create an object in code, just one line.
	public linkMetaData(String att, String attval, String medType) {
		this.attribute = att;
		this.attvalue = attval;
		this.mediaType = medType;
	}
	
	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public String getValue() {
		return attvalue;
	}
	public void setAttvalue(String value) {
		this.attvalue = value;
	}
	
	@Override
	public String toString() {
		return "<link " + this.attribute + "=" + "\"" + this.attvalue + "\"" + "/>";
	}
	
}
