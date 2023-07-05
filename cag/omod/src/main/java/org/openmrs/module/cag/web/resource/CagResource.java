package org.openmrs.module.cag.web.resource;

import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.cag.api.CagService;
import org.openmrs.module.cag.cag.Cag;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.api.PageableResult;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.NeedsPaging;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

@Resource(name = RestConstants.VERSION_1 + "/cag/cag", supportedClass = Cag.class, supportedOpenmrsVersions = { "1.8.* - 9.9.*" })
public class CagResource extends DelegatingCrudResource<Cag> {
	
	@Override
	public Cag getByUniqueId(String uuid) {
		return getService().getCagByUuid(uuid);
	}
	
	@Override
	protected void delete(Cag cag, String s, RequestContext requestContext) throws ResponseException {
		getService().voidCag(cag);
	}
	
	@Override
	public Cag newDelegate() {
		return new Cag();
	}
	
	@Override
	public Cag save(Cag cag) {
		getService().saveCag(cag);
		return getService().getCagByUuid(cag.getUuid());
	}
	
	@Override
	public void purge(Cag cag, RequestContext requestContext) throws ResponseException {
		getService().voidCag(cag);
	}
	
	@Override
	public DelegatingResourceDescription getRepresentationDescription(Representation rep) {
		DelegatingResourceDescription description = null;
		if (rep instanceof FullRepresentation) {
			description = new DelegatingResourceDescription();
			description.addProperty("uuid");
			description.addProperty("name");
			description.addProperty("description");
			description.addProperty("village");
			description.addProperty("constituency", Representation.REF);
			description.addSelfLink();
		} else {
			description = new DelegatingResourceDescription();
			description.addProperty("uuid");
			description.addProperty("name");
			description.addProperty("description");
			description.addProperty("village");
			description.addProperty("constituency", Representation.REF);
			description.addSelfLink();
			description.addLink("full", ".?v=" + RestConstants.REPRESENTATION_FULL);
		}
		return description;
	}
	
	@Override
	public NeedsPaging<Cag> doGetAll(RequestContext context) throws ResponseException {
		List<Cag> cagList = Context.getService(CagService.class).getCagList();
		return new NeedsPaging<Cag>(cagList, context);
	}
	
	@Override
	public SimpleObject getAll(RequestContext context) throws ResponseException {
		return this.doGetAll(context).toSimpleObject(null);
	}
	
	@Override
	protected PageableResult doSearch(RequestContext context) {
		//Search logic
		return super.doSearch(context);
	}
	
	private CagService getService() {
		return Context.getService(CagService.class);
	}
}
