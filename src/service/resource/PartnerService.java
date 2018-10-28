package service.resource;

import java.util.Set;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

import service.represntation.OrderRequest;
import service.represntation.PartnerRepresentation;
import service.represntation.PartnerRequest;
import service.represntation.ProductRequest;
import service.represntation.OrderRepresentation;

@WebService
public interface PartnerService {
	
	public Set<PartnerRepresentation> getPartner();
	public PartnerRepresentation getPartner(String partnerId);
	public PartnerRepresentation createPartner(PartnerRequest  partnerRequest);
	public PartnerRepresentation addProductToPartner(String partnerID, ProductRequest productRequest);
	//NOTE TO JULIANA: This is not needed in the partner resource.
	//public OrderRepresentation pushOrderToPartner(OrderRequest orderRequest);
	public Response deletePartner(String partnerId);
	
}
