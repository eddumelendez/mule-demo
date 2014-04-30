package org.mule.demo.ldap;

import org.junit.Test;
import org.mule.api.MuleMessage;
import org.mule.module.client.MuleClient;

public class MuleFunctionalTestCase extends AbstractEmbeddedADSTest {

	@Override
	protected String getConfigResources() {
		return "mule-config.xml";
	}

	@Test
	public void testLdapSearch() throws Exception {
		MuleClient client = new MuleClient(muleContext);
		MuleMessage muleMessage = client.send("vm://ldapVm", null, null);
		String payload = muleMessage.getPayloadAsString();
		System.out.println(payload);
	}
	
}
