package net.eddumelendez.mule;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.mule.api.MuleMessage;
import org.mule.module.client.MuleClient;
import org.mule.tck.junit4.FunctionalTestCase;

public class MuleFunctionalTestCase extends FunctionalTestCase {

	@Override
	protected String getConfigResources() {
		return "mule-config.xml";
	}

	@Test
	public void testListCollections() throws Exception {
		MuleClient client = new MuleClient(muleContext);
		String collections = Arrays.asList("names","system.indexes").toString();
		MuleMessage muleMessage = client.send("vm://mongoOperation", null, null);
		String payload = muleMessage.getPayloadAsString();
		Assert.assertEquals(collections, payload);
	}
	
}
