package org.openmrs.module.cag.web.resource;

import org.openmrs.module.cag.cag.Cag;
import org.openmrs.module.cag.cag.CagVisit;
import org.openmrs.module.cag.web.controller.CagController;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.api.PageableResult;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;
import org.openmrs.module.webservices.rest.web.response.ResponseException;
import org.springframework.stereotype.Component;

@Resource(name = RestConstants.VERSION_1 + CagController.CAG_VISIT_NAMESPACE, supportedClass = Cag.class, supportedOpenmrsVersions = {
        "1.8.*", "2.1.*", "2.4.*" })
@Component
public class CagVisitResource extends DelegatingCrudResource<CagVisit> {
	
	@Override
	public Object update(String uuid, SimpleObject propertiesToUpdate, RequestContext context) throws ResponseException {
		return super.update(uuid, propertiesToUpdate, context);
	}
	
	@Override
	protected PageableResult doGetAll(RequestContext context) throws ResponseException {
		return super.doGetAll(context);
	}
	
	@Override
	public DelegatingResourceDescription getCreatableProperties() throws ResourceDoesNotSupportOperationException {
		return super.getCreatableProperties();
	}
	
	@Override
	public DelegatingResourceDescription getUpdatableProperties() throws ResourceDoesNotSupportOperationException {
		return super.getUpdatableProperties();
	}
	
	@Override
	public CagVisit getByUniqueId(String s) {
		return null;
	}
	
	@Override
	protected void delete(CagVisit cagVisit, String s, RequestContext requestContext) throws ResponseException {
		
	}
	
	@Override
	public void purge(CagVisit cagVisit, RequestContext requestContext) throws ResponseException {
		
	}
	
	@Override
	public CagVisit newDelegate() {
		return null;
	}
	
	@Override
	public CagVisit save(CagVisit cagVisit) {
		return null;
	}
	
	@Override
	public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
		return null;
	}
}
