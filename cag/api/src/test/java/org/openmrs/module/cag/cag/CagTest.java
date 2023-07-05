package org.openmrs.module.cag.cag;

import junit.framework.TestCase;

public class CagTest extends TestCase {
	
	public void setUp() throws Exception {
		super.setUp();
	}
	
	public void tearDown() throws Exception {
	}
	
	public void testTestGetName() {
		
		Cag cag = new Cag();
		assertNull(cag.getName());
		
		cag.setName("Maks Community Group");
		
		assertNotNull(cag.getName());
		
		assertTrue("Cag's name length should be > 0", cag.getName().length()>0);
	}
}
