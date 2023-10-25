package org.openmrs.module.cag.web.resource;

import org.openmrs.api.context.Context;
import org.openmrs.module.cag.api.CagService;
import org.openmrs.module.cag.cag.CagEncounter;
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
import org.openmrs.module.webservices.rest.web.response.ResponseException;

@Resource(name = RestConstants.VERSION_1 + CagController.CAG_ENCOUNTER_NAMESPACE, supportedClass = CagEncounter.class, supportedOpenmrsVersions = {
        "1.8.*", "2.1.*", "2.4.*" })
public class CagEncounterResource extends DelegatingCrudResource<CagEncounter> {
	
	@Override
	protected void delete(CagEncounter cagEncounter, String reason, RequestContext requestContext) throws ResponseException {
		System.out.println("Deleting a Cag Encounter!!!!");
		getService().deleteCagEncounter(cagEncounter.getUuid());
	}
	
	@Override
	public CagEncounter newDelegate() {
		return new CagEncounter();
	}
	
	@Override
	public CagEncounter save(CagEncounter cagEncounter) {
		//		System.out.println("CagEncounterResource save called!!");
		return getService().saveCagEncounter(cagEncounter);
	}
	
	@Override
	public void purge(CagEncounter cagEncounter, RequestContext requestContext) throws ResponseException {
		getService().deleteCagEncounter(cagEncounter.getUuid());
	}
	
	@Override
	protected PageableResult doGetAll(RequestContext context) throws ResponseException {
		return super.doGetAll(context);
	}
	
	@Override
	public CagEncounter getByUniqueId(String uuid) {
		System.out.println("getByUniqueId id being called!!!");
		CagEncounter cagEncounter = getService().getCagEncounterByUuid(uuid);
		System.out.println(cagEncounter);
		
		return cagEncounter;
	}
	
	@Override
	public DelegatingResourceDescription getCreatableProperties() {
		DelegatingResourceDescription description = new DelegatingResourceDescription();
		description.addProperty("cag");
		description.addProperty("cagVisit");
		description.addProperty("nextEncounterDate");
		description.addProperty("encounters");
		return description;
	}
	
	@Override
	public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
		DelegatingResourceDescription description = null;
		
		if (representation instanceof DefaultRepresentation) {
			description = new DelegatingResourceDescription();
			
			description.addProperty("uuid");
			description.addProperty("nextEncounterDate");
			
			description.addSelfLink();
			description.addLink("full", ".?v=full");
		} else if (representation instanceof FullRepresentation) {
			description = new DelegatingResourceDescription();
			
			description.addProperty("uuid");
			description.addProperty("nextEncounterDate");
			
			description.addSelfLink();
		} else {
			description.addProperty("uuid");
			description.addProperty("nextEncounterDate");
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
