package org.mule.demo.ldap;

import java.io.File;

import org.apache.directory.server.core.DefaultDirectoryService;
import org.apache.directory.server.core.schema.SchemaInterceptor;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.mule.tck.junit4.FunctionalTestCase;
import org.mule.util.FileUtils;
import org.springframework.security.ldap.server.ApacheDSContainer;

public abstract class AbstractEmbeddedADSTest extends FunctionalTestCase {

	private static ApacheDSContainer ldapServer;
	public static int LDAP_PORT = 10389;
	public static File WORKING_DIRECTORY = new File(
			System.getProperty("java.io.tmpdir") + File.separator
					+ "ldap-connector-junit-server");

	@BeforeClass
	public static void startLdapServer() throws Exception {
		FileUtils.deleteDirectory(WORKING_DIRECTORY);

		ldapServer = new ApacheDSContainer("dc=jayway,dc=se",
				"classpath:setup_data.ldif");
		ldapServer.setWorkingDirectory(WORKING_DIRECTORY);
		ldapServer.setPort(LDAP_PORT);
		DefaultDirectoryService directoryService = ldapServer.getService();
		directoryService.setAllowAnonymousAccess(true);
		directoryService.setAccessControlEnabled(true);
		directoryService.setShutdownHookEnabled(true);

		directoryService.getInterceptors().add(new SchemaInterceptor());
		ldapServer.afterPropertiesSet();
		directoryService.getSchemaService().getRegistries();
	}

	@AfterClass
	public static void stopLdapServer() throws Exception {
		if (ldapServer != null) {
			ldapServer.stop();
		}
	}

}
