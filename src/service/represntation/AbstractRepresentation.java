package service.represntation;

import java.util.ArrayList;
import service.represntation.linkMetaData;

public abstract class AbstractRepresentation {
	public AbstractRepresentation() {}
	
	protected ArrayList<linkMetaData> metaDataLinkAttributes = new ArrayList<>();
	
	public ArrayList<String> getMetaDataLinks() {
		ArrayList<String> resourceLinks = new ArrayList<>();
		
		for (linkMetaData lmd : metaDataLinkAttributes) {
			resourceLinks.add(lmd.toString());
		}
		
		return resourceLinks;
	}
	
	public void addMetaDataToLink(linkMetaData lMd) {
		metaDataLinkAttributes.add(lMd);
	}
}
