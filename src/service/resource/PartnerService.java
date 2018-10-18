package service.resource;

import java.util.Set;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

import service.represntation.OrderRequest;
import service.represntation.PartnerRepresentation;
import service.represntation.PartnerRequest;
import service.represntation.ProductRequest;

@WebService
public interface PartnerService {
	
	public Set<PartnerRepresentation> getPartner();
	public PartnerRepresentation getPartner(String partnerId);
	public PartnerRepresentation createPartner(PartnerRequest  partnerRequest);
	public PartnerRepresentation addProductToPartner(String partnerID, ProductRequest productRequest);
	public PartnerRepresentation pushOrderToPartner(String partnerID, OrderRequest orderRequest);
	public Response deletePartner(String partnerId);
	
}
