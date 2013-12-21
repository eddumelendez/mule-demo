package net.eddumelendez.mule;

import java.util.HashMap;
import java.util.Map;

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
	public void testGreeting() throws Exception {
		MuleClient client = new MuleClient(muleContext);
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", "Eddu");
		MuleMessage muleMessage = client.send("vm://test", params, null);
		String payload = muleMessage.getPayloadAsString();
		Assert.assertEquals("Hello Eddu", payload);
	}
	
}
