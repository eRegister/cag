package org.openmrs.module.cag.api.impl;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.cag.api.CagService;
import org.openmrs.module.cag.cag.Cag;
import org.openmrs.test.BaseModuleContextSensitiveTest;

public class CagServiceImplTest extends BaseModuleContextSensitiveTest {
	
	CagService cagService;
	
	String uuid;
	
	@Override
	public Boolean useInMemoryDatabase() {
		return true;
	}
	
	@Before
	public void initializeCagService() throws Exception {
		initializeInMemoryDatabase();
		
		cagService = Context.getService(CagService.class);
		uuid = "0D4123CE-0FA6-412B-AEF7-0174E355FA1D";
	}
	
	@Test
	public void testAddCagShouldAddACag() {
		Cag cag = new Cag();
		cag.setUuid(uuid);
		cag.setName("Community Cag");
		cag.setDescription("Community Cag");
		cag.setCreator(Context.getUserService().getUser(1));
		cag.setDateCreated(new Date());
		
		List<Cag> cagsList = cagService.getCagList();
		Assert.assertEquals(cagsList.size(), 0);
		
		cagService.saveCag(cag);
		
		Cag savedCag = cagService.getCagByUuid(uuid);
		
		Assert.assertNotNull(savedCag.getId());
		Assert.assertEquals(cag.getUuid(), savedCag.getUuid());
		
		Assert.assertEquals(cagService.getCagList().size(), 1);
		
	}
	
	@After
	public void cleanUpObjects() {
		cagService = null;
		uuid = null;
	}
}
