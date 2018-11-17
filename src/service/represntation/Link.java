package service.represntation;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Link")
public class Link {
	private String rel;
	private String url;
	private String mediaType;
	
	public Link() {}
	
	//Constructor initializes the link data. This makes it easier to create an object in code, just one line.
	public Link(String rel, String url, String medType) {
		this.rel = rel;
		this.url = url;
		this.mediaType = medType;
	}
	
	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	
	@Override
	public String toString() {
		return "<link "
				+ "\"rel\"=\"" + this.rel + "\"" 
				+ "\"url\"=\"" + this.url + "\"" 
				+ "\"mediaType\"=\"" + this.mediaType + "\"" 
				+ "/>";
	}
	
}
