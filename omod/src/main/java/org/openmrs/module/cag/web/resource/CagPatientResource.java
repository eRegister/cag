package org.openmrs.module.cag.web.resource;

import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.cag.api.CagService;
import org.openmrs.module.cag.cag.Cag;
import org.openmrs.module.cag.cag.CagPatient;
import org.openmrs.module.cag.web.controller.CagController;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.api.PageableResult;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.NeedsPaging;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

@Resource(name = RestConstants.VERSION_1 + CagController.CAG_NAMESPACE + "/patients", supportedClass = CagPatient.class, supportedOpenmrsVersions = {
        "1.8.*", "2.1.*", "2.4.*" })
public abstract class CagPatientResource extends DelegatingCrudResource<CagPatient> {
	
	@Override
	protected void delete(CagPatient cagPatient, String reason, RequestContext requestContext) throws ResponseException {
		getService().deletePatientFromCag(cagPatient);
	}
	
	@Override
	public CagPatient newDelegate() {
		return new CagPatient();
	}
	
	@Override
	public CagPatient save(CagPatient cagPatient) {
		
		getService().saveCagPatient(cagPatient);
		
		return getService().getCagPatientById(cagPatient.getCagPatientId());
	}
	
	@Override
	public void purge(CagPatient cagPatient, RequestContext requestContext) throws ResponseException {
		getService().deletePatientFromCag(cagPatient);
	}
	
	@Override
	public DelegatingResourceDescription getCreatableProperties() {
		DelegatingResourceDescription description = new DelegatingResourceDescription();
		description.addProperty("cag_id");
		description.addProperty("patient_id");
		description.addProperty("status");
		return description;
	}
	
	@Override
	public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
		DelegatingResourceDescription description = null;
		
		if (representation instanceof DefaultRepresentation) {
			description = new DelegatingResourceDescription();
			
			description.addProperty("cagPatientId");
			description.addProperty("status");
			description.addProperty("cag_id");
			description.addProperty("patient_id");
			
			description.addSelfLink();
			description.addLink("full", ".?v=full");
		} else if (representation instanceof FullRepresentation) {
			description = new DelegatingResourceDescription();
			
			description.addProperty("status");
			description.addProperty("cag_id");
			description.addProperty("patient_id");
			
			description.addSelfLink();
		} else {
			description = new DelegatingResourceDescription();
			description.addProperty("status");
			description.addSelfLink();
		}
		
		return description;
	}
	
	//        @Override
	//        protected PageableResult doGetAll(RequestContext context) throws ResponseException {
	//            Cag cag =new Cag(10);
	//            return new NeedsPaging<CagPatient>(getService().getCagPatientList(10), context);
	//        }
	
	private CagService getService() {
		return Context.getService(CagService.class);
	}
}
