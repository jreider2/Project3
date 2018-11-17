package service.represntation;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;

import service.represntation.Link;

public abstract class AbstractRepresentation {
	
	@XmlElement(name="link", namespace="")
	protected ArrayList<Link> links = new ArrayList<>();
	
	public AbstractRepresentation() {}
	
	public ArrayList<Link> getLinks() {
		return links;
	}
	
	public void addLink(Link link) {
		links.add(link);
	}
}
